package cn.lizhaoloveit.springframework.beans.converter;

public class IntegerTypeConverter implements cn.lizhaoloveit.springframework.beans.converter.TypeConverter {
	@Override
	public Integer convert(String source) {
		return Integer.parseInt(source);
	}
	@Override
	public boolean isType(Class<?> clazz) {
		return clazz == Integer.class;
	}
}
