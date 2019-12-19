package org.tinos.deta.cluster;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//task 20191220-20191222 daytime
//ͨ��scale ���������������ż��� �ھۡ�
//Theory yaoguang.luo 20191219�� ŷ�����
//Application yaoguang.luo
//ע�⣺����������ɾ������map�������������㣬���ǿ��ǵ� java���������ָ����ʽ������ȡ��ɾ��˼�룬�����ƻ������ϲ��߼���
//С����м�����Ҫ�����������޸ġ�
public class Fusion{
	public static Map<Double, List<Position2D>> fusionPosition2DwithHeart
	(Map<Double, List<Position2D>> groups, Map<Double, Position2D> groupsHeart, double scale){
		//��ʼ
		Map<Double, List<Position2D>> output= new HashMap<>();
		Map<Double, Position2D> outputHeart= new HashMap<>();
		//���űȽ����ľ���
		Iterator<Double> outLoop= groupsHeart.keySet().iterator();
		//С�ھ������ھ�
		//HereOut:
		while(outLoop.hasNext()) {
			double out= outLoop.next();
			Iterator<Double> inLoop= groupsHeart.keySet().iterator();
			HereIn:
				while(inLoop.hasNext()) {
					double in= inLoop.next();
					if(out== in|| output.containsKey(in)) {
						continue HereIn;//out���ھ۲����in���������ӡ�output���۲��
					}
					Position2D inHeart=	groupsHeart.get(in);
					//Position2D outHeart= groupsHeart.get(out);
					//������Ϊjava��ָ�뱻���󻯣�ֱ���޸���λ������������������outputHeart����������
					Position2D outHeart= outputHeart.containsKey(out)
							? outputHeart.get(out): groupsHeart.get(out);
							double distance= Distance.getDistance2D(inHeart, outHeart);
							//�Ƚ� ���ں�
							if(distance< scale) {
								//�Ƚ�����ý
								if(output.containsKey(out)) {
									List<Position2D> outList= output.get(out);
									//����ýin to out ɾ�� in
									List<Position2D> inList= groups.get(in);
									Iterator<Position2D> iterator= inList.iterator();
									while(iterator.hasNext()) {
										outList.add(iterator.next());
									}
									output.put(out, outList);
									//����heart
									Position2D newHeart= Eclid.findCryptionPosition2D(outHeart, inHeart);
									outputHeart.put(out, newHeart);
								}else {//�Ƚ�����ý
									//����ýin to out ��out��ɾ�� in
									List<Position2D> outList= groups.get(out);
									//����ýin to out ɾ�� in
									List<Position2D> inList= groups.get(in);
									Iterator<Position2D> iterator= inList.iterator();
									while(iterator.hasNext()) {
										outList.add(iterator.next());
									}
									output.put(out, outList);
									//����heart
									Position2D newHeart= Eclid.findCryptionPosition2D(outHeart, inHeart);
									outputHeart.put(out, newHeart);
								}
							}else {//�Ƚ� ���ںϣ�
								//�Ƚ�����ý
								if(output.containsKey(out)) {
									//����ý in ɾ�� in
									if(!output.containsKey(in)) {
										List<Position2D> inList= groups.get(in);
										output.put(in, inList);
										//����heart
										outputHeart.put(in, inHeart);	
									}
								}else {//�Ƚ�����ý
									//����ý out��ɾ�� out������ý in ɾ�� in
									if(!output.containsKey(out)) {
										List<Position2D> outList= groups.get(out);
										output.put(out, outList);
										//����heart
										outputHeart.put(out, outHeart);	
									}
									if(!output.containsKey(in)) {
										List<Position2D> inList= groups.get(in);
										output.put(in, inList);
										//����heart
										outputHeart.put(in, inHeart);	
									}
								}
							}
				}

		}
		return output;	
	}
} 