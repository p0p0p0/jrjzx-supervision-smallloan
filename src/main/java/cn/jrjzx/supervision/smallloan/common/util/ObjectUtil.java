package cn.jrjzx.supervision.smallloan.common.util;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import cn.jrjzx.supervision.smallloan.entity.DataOption;
import cn.jrjzx.supervision.smallloan.entity.User;

public class ObjectUtil  {
	public static void main(String[] args) {
		System.out.println(isEqual(null, null));
		User u1= new User();
		u1.setCompanyId(1);
		User u2 = new User();
		u2.setMobile("123456");
		copyPropertiesIgnoreNull(u1, u2);
		System.out.println(u2.getCompanyId()+":"+u2.getMobile());
	}

	public static boolean isEqual(Object o1, Object o2) {
		if (o1 != null) {
			return o1.equals(o2);
		} else if (o2 == null) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEqualByOption(DataOption option, Object oval,
			Object nval) throws Exception {
		String tem = option.getOptionKey();
		String fname = tem.substring(tem.indexOf(".") + 1);
		Field f = oval.getClass().getDeclaredField(fname);
		f.setAccessible(true);
		Object ov = f.get(oval);
		Object nv = f.get(nval);
		return isEqual(ov, nv);
	}

	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	public static void copyPropertiesIgnoreNull(Object src, Object target) {
		BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}
}
