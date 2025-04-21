package Listeners_Utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer_utility implements IRetryAnalyzer{
	
	int count = 0;
	
	int max_retry = 5;
	

	@Override
	public boolean retry(ITestResult result) {
		if(count < max_retry) {
			count++;
			return true;
		}
		return false;
	}
	
}
