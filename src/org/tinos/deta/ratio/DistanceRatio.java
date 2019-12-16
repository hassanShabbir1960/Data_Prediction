package org.tinos.deta.ratio;
import org.tinos.deta.cluster.Position2D;
//Theory: Yaoguang.luo
//һ�ֽ���ͨ�������ֵ�������������������ʹ�÷�����
//Application: Yaoguang.luo
public class DistanceRatio{
	//��ȡб���ݶ�
	public static double getDistanceRatio2D(Position2D begin, Position2D end) {
		double x= begin.getX()- end.getX();
		double y= begin.getY()- end.getY();
		//1����
		if(x>=0&& y>=0) {
			return Math.abs(x)/ Math.abs(y);
		}
		//4����
		if(x>= 0 && y<0) {
			return 1+ Math.abs(x)/ Math.abs(y);
		}
		//2����
		if(x< 0 && y>= 0) {
			return Math.abs(x)/ Math.abs(y);
		}
		//3����
		if(x< 0&& y< 0) {
			return -1 - Math.abs(x)/ Math.abs(y);
		}
		return Math.abs(x)/ Math.abs(y);
	}
}