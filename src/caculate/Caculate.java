package caculate;

import Fraction.Fractions;

public class Caculate {
	// 定义两个待计算的分数
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

	// 加法计算
	public Fractions addtion() {
		Fractions result = new Fractions();
		int result_numerator, min; // 相加后的分子以及两分数分母的最小公倍数
		min = Fractions.minCommonMultiple(fractions1.getDenominator(), fractions2.getDenominator());
		result_numerator = (min / fractions1.getDenominator()) * fractions1.getNumerator()
				+ (min / fractions2.getDenominator()) * fractions2.getNumerator();
		result.setValue(result_numerator, min);
		return result;
	}

	// 减法计算
	public Fractions subtraction() {
		Fractions result = new Fractions();
		int result_numerator, min; // 相减后的分子以及两分数分母的最小公倍数
		min = Fractions.minCommonMultiple(fractions1.getDenominator(), fractions2.getDenominator());
		result_numerator = (min / fractions1.getDenominator()) * fractions1.getNumerator()
				- (min / fractions2.getDenominator()) * fractions2.getNumerator();
		result.setValue(result_numerator, min);
		return result;
	}

	// 乘法计算
	public Fractions multiplication() {
		Fractions result = new Fractions();
		int result_numerator, result_denominator; // 相乘后的分子和分母
		result_numerator = fractions1.getNumerator() * fractions2.getNumerator();
		result_denominator = fractions1.getDenominator() * fractions2.getDenominator();
		result.setValue(result_numerator, result_denominator);
		return result;
	}

	// 除法计算
	public Fractions division() {
		Fractions result = new Fractions();
		int result_numerator, result_denominator; // 相除后的分子和分母
		// 分数相除问题转换成分数相乘问题
		result_numerator = fractions1.getNumerator() * fractions2.getDenominator();
		result_denominator = fractions1.getDenominator() * fractions2.getNumerator();
		result.setValue(result_numerator, result_denominator);
		return result;
	}
}
