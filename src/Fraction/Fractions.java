package Fraction;

public class Fractions {
	private int numerator; // 分子
	private int denominator; // 分母
	// 无参构造器

	public Fractions() {
	}

	// 设置分子分母
	public void setValue(int numerator, int denominator) {
		int temp = maxCommonDivisor(denominator, numerator); // temp为最大公约数
		this.numerator = numerator / temp;
		this.denominator = denominator / temp;
	}

	// 求最大公约数
	public static int maxCommonDivisor(int d, int n) {
		if (d < n) {// 保证d>n,若d<n,则进行数据交换
			int temp = d;
			d = n;
			n = temp;
		}
		while (d % n != 0) {// 在余数不能为0时,进行循环
			int temp = d % n;
			d = n;
			n = temp;
		}
		return n;// 返回最大公约数
	}

	// 求最小公倍数
	public static int minCommonMultiple(int m, int n) {
		return m * n / maxCommonDivisor(m, n);
	}

	// 打印分数
	public String getFraction() {
		return (this.numerator + "/" + this.denominator).toString();
	}

	// 获取分子
	public int getNumerator() {
		return this.numerator;
	}

	// 获取分母
	public int getDenominator() {
		return this.denominator;
	}
}
