package org.tinos.deta.cluster;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//task 20191220-20191222 daytime
//ͨ��scale ���������������ż��� �ھۡ�
//Theory yaoguang.luo 20191219�� ŷ�����
//Application yaoguang.luo
public class Fusion{
	public static Map<Double, List<Position2D>> fusionPosition2DwithHeart
	(Map<Double, List<Position2D>> groups, Map<Double, Position2D> groupsHeart, double scale){
		//��ʼ
		Map<Double, List<Position2D>> output= new HashMap<>();
		Map<Double, Position2D> outputHeart= new HashMap<>();
		//���űȽ����ľ���
		Iterator<Double> outLoop= groupsHeart.keySet().iterator();
		Iterator<Double> inLoop= groupsHeart.keySet().iterator();
		//С�ڽ������ھ�
		HereOut:
			while(outLoop.hasNext()) {
				double out= outLoop.next();
				HereIn:
					while(inLoop.hasNext()) {
						double in= inLoop.next();
						if(out!= in) {
							Position2D inHeart=	groupsHeart.get(in);
							Position2D outHeart= groupsHeart.get(out);
							double distance= Distance.getDistance2D(inHeart, outHeart);
							if(distance< scale) {
								//fusion
								if(!output.containsKey(out)) {
									if(!groups.containsKey(out)) {
										continue HereOut;
									}
									if(!groups.containsKey(in)) {
										continue HereIn;
									}
									List<Position2D> outList= groups.get(out);
									List<Position2D> inList= groups.get(in);
									Iterator<Position2D> iterator= inList.iterator();
									while(iterator.hasNext()) {
										outList.add(iterator.next());
									}
									output.put(out, outList);
									//����heart
									Position2D newHeart= Eclid.findCryptionPosition2D(outHeart, inHeart);
									outputHeart.put(out, newHeart);
									//�޳����ھ۶���
									groups.remove(in);
								}else {
									List<Position2D> outList= output.get(out);
									List<Position2D> inList= groups.get(in);
									Iterator<Position2D> iterator= inList.iterator();
									while(iterator.hasNext()) {
										outList.add(iterator.next());
									}
									output.put(out, outList);
									//����heart
									Position2D outHeartForOutput= outputHeart.get(out); 
									Position2D newHeart= Eclid.findCryptionPosition2D(outHeartForOutput, inHeart);
									outputHeart.put(out, newHeart);
									//�޳����ھ۶���
									groups.remove(in);
								}
								groups.get(out);
							}
						}
					}
			}
		return output;	
	}
} 