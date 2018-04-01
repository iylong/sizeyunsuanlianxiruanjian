package Fraction;

public class Fractions {
	private int numerator; // ����
	private int denominator; // ��ĸ
	// �޲ι�����

	public Fractions() {
	}

	// ���÷��ӷ�ĸ
	public void setValue(int numerator, int denominator) {
		int temp = maxCommonDivisor(denominator, numerator); // tempΪ���Լ��
		this.numerator = numerator / temp;
		this.denominator = denominator / temp;
	}

	// �����Լ��
	public static int maxCommonDivisor(int d, int n) {
		if (d < n) {// ��֤d>n,��d<n,��������ݽ���
			int temp = d;
			d = n;
			n = temp;
		}
		while (d % n != 0) {// ����������Ϊ0ʱ,����ѭ��
			int temp = d % n;
			d = n;
			n = temp;
		}
		return n;// �������Լ��
	}

	// ����С������
	public static int minCommonMultiple(int m, int n) {
		return m * n / maxCommonDivisor(m, n);
	}

	// ��ӡ����
	public String getFraction() {
		return (this.numerator + "/" + this.denominator).toString();
	}

	// ��ȡ����
	public int getNumerator() {
		return this.numerator;
	}

	// ��ȡ��ĸ
	public int getDenominator() {
		return this.denominator;
	}
}
