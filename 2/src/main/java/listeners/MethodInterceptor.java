package listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import constants.FrameworkConstants;
import utilities.ExcelUtils;

public final class MethodInterceptor implements IMethodInterceptor {

	@Override
	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
		List<Map<String, String>> list;
		List<IMethodInstance> result = null;

		list = ExcelUtils.getTestDetailsForInterceptor(FrameworkConstants.getTestManagerSheetName());
		result = new ArrayList<>();

		for (int i = 0; i < methods.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (methods.get(i).getMethod().getMethodName().equalsIgnoreCase(list.get(j).get("TESTCASENAME"))
						&& list.get(j).get("EXECUTE").equalsIgnoreCase("yes")) {
					result.add(methods.get(i));
				}
			}
		}

		return result;
	}

}
