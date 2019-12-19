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
//Theory yaoguang.luo
//Application yaoguang.luo
public class Fissile{
	public static Map<Double, List<Position2D>> fissilePosition2D(List<Position2D> groups, double scale) {
		Map<Double, List<Position2D>> distanceGroups= new HashMap<>();
		Map<Double, Position2D> distanceHeart= new HashMap<>();
		Iterator<Position2D> iterator= groups.iterator();
		while(iterator.hasNext()) {
			Position2D position2D= iterator.next();
			if(distanceGroups.isEmpty()) {
				List<Position2D> list= new ArrayList<>();
				list.add(position2D);
				distanceGroups.put(0.0, list);
				distanceHeart.put(0.0, position2D);
			}else {
				//����������
				//������ƥ������������´棬���Ǿ����롣
				Iterator<Double> iteratorScale= distanceHeart.keySet().iterator();
				//Iterator<Double> iteratorScale= distanceGroups.keySet().iterator();
				while(iteratorScale.hasNext()) {
					Double doubleScale= iteratorScale.next();
					Position2D currenctHeart= distanceHeart.get(doubleScale);
					double distance= Distance.getDistance2D(currenctHeart, position2D);
					if(distance< scale) {
						//����õ��µ�����
						@SuppressWarnings("unused")
						Position2D newHeart= Eclid.findCryptionPosition2D(currenctHeart, position2D);
						//ɾ����ǰ�������꼯���������꼯
						
						//ɾ����ǰ�������ݣ�������������
					}
				}
			}
		}
		return null;	
	}
}