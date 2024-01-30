package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should conatins only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
    public Binary(String number) {
		for (int i = 0; i < number.length(); i++) {
			// check each character if it's not 0 or 1
			char ch=number.charAt(i);
			if(ch!='0' && ch!='1') {
				number="0"; // if not store "0" and end the function
				return;
			}
		}
		// remove any trailing zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg)!='0')
				break;
		}
		//beg has the index of the first non zero digit in the number
		this.number=number.substring(beg); // exclude the trailing zeros if any
		// uncomment the following code
		
		if(this.number=="") { // replace empty strings with a single zero
			this.number="0";
		}
		
    }
	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}
	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1+num2</i>.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		
		int carry=0;
		String num3="";  
		while(ind1>=0 ||  ind2>=0 || carry!=0) 
		{
			int sum=carry; 
			if(ind1>=0){ 
				sum += (num1.number.charAt(ind1)=='1')? 1:0; 
				ind1--; 
			}
			if(ind2>=0){ 
				sum += (num2.number.charAt(ind2)=='1')? 1:0; 
				ind2--; 
			}
			carry=sum/2; 
			sum=sum%2;  
			num3 =( (sum==0)? "0":"1")+num3; 
		}
		Binary result=new Binary(num3);  
		return result;
		
	}

    /**
	 * Perform OR logical operation with two binary variables.
	 *
	 * @param binary1 The first binary variable.
	 * @param binary2 The second binary variable.
	 * @return The output of the OR operator on two binary values.
	 */

    public static Binary or(Binary binary1, Binary binary2) {
		int len1 = binary1.number.length();
		int len2 = binary2.number.length();

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < Math.max(len1, len2); i++) {
			char digit1 = (i < len1) ? binary1.number.charAt(len1 - 1 - i) : '0';
			char digit2 = (i < len2) ? binary2.number.charAt(len2 - 1 - i) : '0';

			char orResult = (digit1 == '1' || digit2 == '1') ? '1' : '0';
			result.insert(0, orResult);
		}

		return new Binary(result.toString());
	}

	/**
	 * Perform AND logical operation with two binary variables.
	 *
	 * @param binary1 The first binary variable.
	 * @param binary2 The second binary variable.
	 * @return A binary variable with a value of AND operation between the two
	 *         binary
	 *         numbers.
	 */
	public static Binary and(Binary binary1, Binary binary2) {
		int len1 = binary1.number.length();
		int len2 = binary2.number.length();

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < Math.max(len1, len2); i++) {
			char digit1 = (i < len1) ? binary1.number.charAt(len1 - 1 - i) : '0';
			char digit2 = (i < len2) ? binary2.number.charAt(len2 - 1 - i) : '0';

			char andResult = (digit1 == '1' && digit2 == '1') ? '1' : '0';
			result.insert(0, andResult);
		}

		return new Binary(result.toString());
	}

	/**
	 * Multiply two binary variables.
	 *
	 * @param binary1 The first binary variable.
	 * @param binary2 The second binary variable.
	 * @return A binary variable with a value of product of the two binary numbers.
	 */
	public static Binary multiply(Binary binary1, Binary binary2) {
		Binary result = new Binary("0");

		for (int i = 0; i < binary2.number.length(); i++) {
			char currentDigit = binary2.number.charAt(binary2.number.length() - 1 - i);

			if (currentDigit == '1') {
				Binary partialProduct = new Binary(binary1.number);
				for (int j = 0; j < i; j++) {
					partialProduct = Binary.add(partialProduct, partialProduct);
				}
				result = Binary.add(result, partialProduct);
			}
		}

		return result;
	}
}	
