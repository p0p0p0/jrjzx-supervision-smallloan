package cn.jrjzx.supervision.smallloan.wrapper;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CustomRequestWrapper extends HttpServletRequestWrapper {

	private String method;
	

	public Map<String, String[]> getParams() {
		return params;
	}

	public void setParams(Map<String, String[]> params) {
		this.params = params;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	
    public String getMethod() {
		return method;
	}


	private Map<String, String[]> params = null;  
  
    public CustomRequestWrapper(HttpServletRequest request) {  
        super(request);  
       params = new HashMap<String, String[]>(request.getParameterMap());
        // RequestDispatcher.forward parameter  
        renewParameterMap(request);  
    }  
  
    @Override  
    public String getParameter(String name) {  
        String result = "";  
          
        Object v = params.get(name);  
        if (v == null) {  
            result = null;  
        } else if (v instanceof String[]) {  
            String[] strArr = (String[]) v;  
            if (strArr.length > 0) {  
                result =  strArr[0];  
            } else {  
                result = null;  
            }  
        } else if (v instanceof String) {  
            result = (String) v;  
        } else {  
            result =  v.toString();  
        }  
          
        return result;  
    }  
  
    @Override  
    public Map getParameterMap() {  
        return params;  
    }  
  
    @Override  
    public Enumeration getParameterNames() {  
        return new Vector(params.keySet()).elements();  
    }  
  
    @Override  
    public String[] getParameterValues(String name) {  
        String[] result = null;  
          
        Object v = params.get(name);  
        if (v == null) {  
            result =  null;  
        } else if (v instanceof String[]) {  
            result =  (String[]) v;  
        } else if (v instanceof String) {  
            result =  new String[] { (String) v };  
        } else {  
            result =  new String[] { v.toString() };  
        }  
          
        return result;  
    }  
  
    private void renewParameterMap(HttpServletRequest req) {  
  
        String queryString = req.getQueryString();  
  
        if (queryString != null && queryString.trim().length() > 0) {  
            String[] params = queryString.split("&");  
  
            for (int i = 0; i < params.length; i++) {  
                int splitIndex = params[i].indexOf("=");  
                if (splitIndex == -1) {  
                    continue;  
                }  
                  
                String key = params[i].substring(0, splitIndex);  
  
                if (!this.params.containsKey(key)) {  
                    if (splitIndex < params[i].length()) {  
                        String value = params[i].substring(splitIndex + 1);  
                        this.params.put(key, new String[] { value });  
                    }  
                }  
            }  
        }  
    }  

}
