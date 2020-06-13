package cn.lizhaoloveit.springframework.beans.aware;


import cn.lizhaoloveit.springframework.beans.factory.BeanFactory;

public interface BeanFactoryAware extends Aware {

	void setBeanFactory(BeanFactory beanFactory);
}
