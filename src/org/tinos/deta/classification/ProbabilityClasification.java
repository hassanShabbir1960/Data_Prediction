package org.tinos.deta.classification;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.tinos.deta.cluster.Ratio;
import org.tinos.deta.cluster.RatioMatrix;
//�����������ͨ�����������ƶ��������������
//˼�룺��Ҷ˹
//ʵ�֣�������
public class ProbabilityClasification{
	public static boolean predictionResult(RatioMatrix input, List<Ratio> right, List<Ratio> error, double scale) {
		double rightRightMean= 0;
		double rightErrorMean= 0;
		double errorRightMean= 0;
		double errorErrorMean= 0;
		//�ɹ���
		Iterator<Ratio> iteratorRight= right.iterator();
		while(iteratorRight.hasNext()) {
			Ratio ratio= iteratorRight.next();
			rightRightMean+= ratio.getRightRatio();
			rightErrorMean+= ratio.getErrorRatio();
		}
		rightRightMean= rightRightMean/ right.size();
		rightErrorMean= rightErrorMean/ right.size();
		//ʧ�ܼ�
		Iterator<Ratio> iteratorError= error.iterator();
		while(iteratorError.hasNext()) {
			Ratio ratio= iteratorError.next();
			errorRightMean+= ratio.getRightRatio();
			errorErrorMean+= ratio.getErrorRatio();
		}
		errorRightMean= errorRightMean/ error.size();
		errorErrorMean= errorErrorMean/ error.size();
		//������
		double predictionRightRight= input.getRightRightRatio()- rightRightMean;
		double predictionRightError= input.getRightErrorRatio()- rightErrorMean;
		double predictionErrorRight= input.getErrorRightRatio()- errorRightMean;
		double predictionErrorError= input.getErrorErrorRatio()- errorErrorMean;
		//��Ħ���
		double pridictionRight= predictionRightRight+ predictionErrorError;
		double pridictionError= predictionRightError+ predictionErrorRight;
		return pridictionRight> pridictionError+ scale? true: false;	
	}
	
	public static String predictionMatrixResult(RatioMatrix input, Map<String, RatioMatrix> groups
			, double scale) {
		String groupKey= null;
		double shortestDistance = 0;
		boolean isFirst= true;
		//��
		double esyn= input.getRightRightRatio()+ input.getErrorErrorRatio();
		//double esny= input.getErrorRightRatio()+ input.getRightErrorRatio();��ʹ�ø��� �� ����������У����������20191217
		double yesyn= input.getRightRightRatio()/ esyn;
		double nesyn= input.getErrorErrorRatio()/ esyn;
		double totalRatio= -yesyn* Math.log(yesyn)- nesyn* Math.log(nesyn);
		//����
		double esyy= input.getRightRightRatio()+ input.getRightErrorRatio();
		double yesyy= input.getRightRightRatio()/ esyy;
		double nesyy= input.getRightErrorRatio()/ esyy;
		double meany= -yesyy* Math.log(yesyy)- nesyy* Math.log(nesyy);
		double esnn= input.getErrorRightRatio()+ input.getErrorErrorRatio();
		double yesnn= input.getErrorRightRatio()/ esnn;
		double nesnn= input.getErrorErrorRatio()/ esnn;
		double meann=-yesnn* Math.log(yesnn)- nesnn* Math.log(nesnn);
		double deta= totalRatio- meany- meann;
		Iterator<String> iterator= groups.keySet().iterator();
		while(iterator.hasNext()) {
			String matrixKey=iterator.next();
			RatioMatrix ratioMatrix= groups.get(matrixKey);
			//����
			double tesyy= ratioMatrix.getRightRightRatio()+ ratioMatrix.getRightErrorRatio();
			double tyesyy= ratioMatrix.getRightRightRatio()/ tesyy;
			double tnesyy= ratioMatrix.getRightErrorRatio()/ tesyy;
			double tmeany= -tyesyy* Math.log(tyesyy)- tnesyy* Math.log(tnesyy);
			double tesnn= ratioMatrix.getErrorRightRatio()+ ratioMatrix.getErrorErrorRatio();
			double tyesnn= ratioMatrix.getErrorRightRatio()/ tesnn;
			double tnesnn= ratioMatrix.getErrorErrorRatio()/ tesnn;
			double tmeann= -tyesnn* Math.log(tyesnn)- tnesnn* Math.log(tnesnn);
			double tdeta= totalRatio- tmeany- tmeann;
			//ȡֵ
			if(isFirst) {
				isFirst= !isFirst;
				shortestDistance= Math.abs(deta- tdeta);
				groupKey= matrixKey;
			}else {
				if(Math.abs(deta- tdeta)< shortestDistance) {
					shortestDistance= Math.abs(deta- tdeta);
					groupKey= matrixKey;	
				}
			}
		}
		//���
		return groupKey;
	}
}