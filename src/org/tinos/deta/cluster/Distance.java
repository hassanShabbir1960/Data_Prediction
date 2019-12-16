package org.tinos.deta.cluster;
//Theory: Yaoguang.luo
//һ�ֽ���ͨ�������ֵ�������������������ʹ�÷�����
//Application: Yaoguang.luo
public class Distance{
	public static double getDistance2D(Position2D begin,Position2D end) {
		double x= begin.getX()- end.getX();
		double y= begin.getY()- end.getY();
		return Math.abs(x)+ Math.abs(y);
	}
	
	public static double getDistance3D(Position3D begin,Position3D end) {
		double x= begin.getX()- end.getX();
		double y= begin.getY()- end.getY();
		double z= begin.getZ()- end.getZ();
		return Math.abs(x)+ Math.abs(y)+ Math.abs(z);
	}
}