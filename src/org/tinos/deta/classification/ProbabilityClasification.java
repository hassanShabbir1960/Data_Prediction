package org.tinos.deta.classification;
import java.util.Iterator;
import java.util.List;
import org.tinos.deta.cluster.Ratio;
//�����������ͨ�����������ƶ��������������
//˼�룺��Ҷ˹
//ʵ�֣�������
public class ProbabilityClasification{
	public static boolean predictionResult(Ratio input, List<Ratio> right, List<Ratio> error, double scale) {
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
		double predictionRightRight= input.rightRatio- rightRightMean;
		double predictionRightError= input.errorRatio- rightErrorMean;
		double predictionErrorRight= input.rightRatio- errorRightMean;
		double predictionErrorError= input.errorRatio- errorErrorMean;
		//��Ħ���
		double pridictionRight= predictionRightRight+ predictionErrorError;
		double pridictionError= predictionRightError+ predictionErrorRight;
		return pridictionRight> pridictionError+ scale? true: false;	
	}
	
	public static boolean predictionMatrixResult(Ratio input, List<Ratio> right, List<Ratio> error, double scale) {
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
		double predictionRightRight= input.rightRatio- rightRightMean;
		double predictionRightError= input.errorRatio- rightErrorMean;
		double predictionErrorRight= input.rightRatio- errorRightMean;
		double predictionErrorError= input.errorRatio- errorErrorMean;
		//��Ħ���
		double pridictionRight= predictionRightRight+ predictionErrorError;
		double pridictionError= predictionRightError+ predictionErrorRight;
		return pridictionRight> pridictionError+ scale? true: false;	
	}
}