package org.tinos.deta.classification;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.tinos.deta.cluster.Distance;
import org.tinos.deta.cluster.Eclid;
import org.tinos.deta.cluster.Position2D;
//�����������ͨ������λ�ƾ����������������
//˼�룺ŷ�����, ������
//ʵ�֣�������
public class PositionClasification{
	public static Map<String, List<Position2D>> addNewPositionWithoutHeart(Map<String
			, List<Position2D>> groups, Position2D position2D){
		double shortestDistance= 0;
		String predictionGroup= null;
		Iterator<String> iterator= groups.keySet().iterator();
		boolean isFirst= true;
		while(iterator.hasNext()) {
			String groupKey= iterator.next();
			List<Position2D> group= groups.get(groupKey);
			Position2D heart= Eclid.findHeartPosition2D(group);
			double distance= Distance.getDistance2D(heart, position2D);
			if(isFirst) {
				isFirst= !isFirst;
				shortestDistance= distance;
			}
			if(shortestDistance< distance) {
				shortestDistance= distance;
				predictionGroup= groupKey;
			}
		}
		//�������
		if(null!= predictionGroup) {
			List<Position2D> group= groups.get(predictionGroup);
			group.add(position2D);
			groups.put(predictionGroup, group);
		}
		return groups;	
	}
	
	public static Map<String, List<Position2D>> addNewPositionWithHeart(Map<String
			, List<Position2D>> groups, Position2D position2D, Map<String, Position2D> hearts){
		double shortestDistance= 0;
		String predictionGroup= null;
		Iterator<String> iterator= groups.keySet().iterator();
		boolean isFirst= true;
		while(iterator.hasNext()) {
			String groupKey= iterator.next();
			List<Position2D> group= groups.get(groupKey);
			Position2D heart= hearts.get(groupKey);
			double distance= Distance.getDistance2D(heart, position2D);
			if(isFirst) {
				isFirst= !isFirst;
				shortestDistance= distance;
			}
			if(shortestDistance< distance) {
				shortestDistance= distance;
				predictionGroup= groupKey;
			}
		}
		//�������
		if(null!= predictionGroup) {
			List<Position2D> group= groups.get(predictionGroup);
			group.add(position2D);
			groups.put(predictionGroup, group);
			//hearts����
			Position2D heart= hearts.get(predictionGroup);
			Position2D CryptHeart= Eclid.findCryptionPosition2D(heart, position2D);
			hearts.put(predictionGroup, CryptHeart);
		}
		return groups;	
	}
}