package org.tinos.deta.desolation;
public class ErrorAsserts{
	//����������ڽ��о������ƥ�� ��ȡ�����Ƿ����ںϸ�״̬��
	public boolean getErrorCaculations(double inputValue, double[] matchValues, double scale) {
		double max= inputValue+ scale;
		double min= inputValue- scale;
		for(int i= 0; i< matchValues.length; i++) {
			if(!(matchValues[i]> max|| matchValues[i]< min)) {
				return true;
			}
		}
		return false;
	}
}
