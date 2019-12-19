package org.tinos.deta.classification;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.tinos.deta.cluster.Distance;
import org.tinos.deta.cluster.Eclid;
import org.tinos.deta.cluster.Position2D;
//task 20191219 daytime
//ͨ��scale ���������� ���������ѡ�
//Theory yaoguang.luo 20191219
//Application yaoguang.luo
public class Fissile{
	public static Map<Double, List<Position2D>> fissilePosition2D(List<Position2D> groups, double scale) {
		Map<Double, List<Position2D>> distanceGroups= new HashMap<>();
		Map<Double, Position2D> distanceHeart= new HashMap<>();
		Iterator<Position2D> iterator= groups.iterator();
		double i= 0.0;
		Here:
		while(iterator.hasNext()) {
			Position2D position2D= iterator.next();
			if(distanceGroups.isEmpty()) {
				List<Position2D> list= new ArrayList<>();
				list.add(position2D);
				distanceGroups.put(i, list);
				distanceHeart.put(i, position2D);
			}else {
				//����������
				//������ƥ������������´棬���Ǿ����롣
				Iterator<Double> iteratorScale= distanceHeart.keySet().iterator();
				boolean isFind= false;
				while(iteratorScale.hasNext()) {
					Double doubleScale= iteratorScale.next();
					Position2D currenctHeart= distanceHeart.get(doubleScale);
					double distance= Distance.getDistance2D(currenctHeart, position2D);
					if(distance< scale) {
						//����õ��µ�����
						Position2D newHeart= Eclid.findCryptionPosition2D(currenctHeart, position2D);
						//ɾ����ǰ�������꼯���������꼯
						List<Position2D> list= distanceGroups.get(doubleScale);
						list.add(position2D);
						distanceGroups.put(doubleScale, list);
						//ɾ����ǰ�������ݣ�������������
						distanceHeart.put(doubleScale, newHeart);
						//�ҵ�
						isFind= true;
						//���Ԥ�� ������Ӧ״̬ ���԰� continue ʡ�ԡ�
						continue Here;
					}	
				}
				if(!isFind) {
					List<Position2D> list= new ArrayList<>();
					list.add(position2D);
					distanceGroups.put(++i, list);
					distanceHeart.put(i, position2D);	
				}
			}
		}
		return distanceGroups;	
	}
}