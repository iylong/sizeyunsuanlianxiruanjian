package caculate;

import Fraction.Fractions;

public class Caculate {
	// ��������������ķ���
	Fractions fractions1;
	Fractions fractions2;

	public Caculate() {
	}

	public Caculate(Fractions fractions1, Fractions fractions2) {
		this.fractions1 = fractions1;
		this.fractions2 = fractions2;
	}

	public void setOperationNum(Fractions fractions1, Fractions fractions2) {
		this.fractions1 = fractions1;
		this.fractions2 = fractions2;
	}

	// �ӷ�����
	public Fractions addtion() {
		Fractions result = new Fractions();
		int result_numerator, min; // ��Ӻ�ķ����Լ���������ĸ����С������
		min = Fractions.minCommonMultiple(fractions1.getDenominator(), fractions2.getDenominator());
		result_numerator = (min / fractions1.getDenominator()) * fractions1.getNumerator()
				+ (min / fractions2.getDenominator()) * fractions2.getNumerator();
		result.setValue(result_numerator, min);
		return result;
	}

	// ��������
	public Fractions subtraction() {
		Fractions result = new Fractions();
		int result_numerator, min; // �����ķ����Լ���������ĸ����С������
		min = Fractions.minCommonMultiple(fractions1.getDenominator(), fractions2.getDenominator());
		result_numerator = (min / fractions1.getDenominator()) * fractions1.getNumerator()
				- (min / fractions2.getDenominator()) * fractions2.getNumerator();
		result.setValue(result_numerator, min);
		return result;
	}

	// �˷�����
	public Fractions multiplication() {
		Fractions result = new Fractions();
		int result_numerator, result_denominator; // ��˺�ķ��Ӻͷ�ĸ
		result_numerator = fractions1.getNumerator() * fractions2.getNumerator();
		result_denominator = fractions1.getDenominator() * fractions2.getDenominator();
		result.setValue(result_numerator, result_denominator);
		return result;
	}

	// ��������
	public Fractions division() {
		Fractions result = new Fractions();
		int result_numerator, result_denominator; // �����ķ��Ӻͷ�ĸ
		// �����������ת���ɷ����������
		result_numerator = fractions1.getNumerator() * fractions2.getDenominator();
		result_denominator = fractions1.getDenominator() * fractions2.getNumerator();
		result.setValue(result_numerator, result_denominator);
		return result;
	}
}
