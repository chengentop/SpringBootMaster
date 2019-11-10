package com.master.core.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration.Node;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.ConfigurationNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 系统配置参数
 * @author Rofly
 */
public class ParamUtil {
	private static Logger log = LoggerFactory.getLogger(ParamUtil.class);
	
	private static final String propFile = "core.properties";
	private static final String validatorFile = "validator.xml";
	
	private static ParamUtil instance = new ParamUtil();
	private static PropertiesConfiguration propData;
	private static XMLConfiguration validatorData;
	private static PropertiesConfiguration smsContentTemplateData;
	public static ParamUtil getInstance(){
		return instance;
	}
	
	public static PropertiesConfiguration getProperties(){
		synchronized (propFile.intern()) {
			if(null == propData){
				log.info("###初始化参数池，加载配置文件{}", propFile);
				instance.loadProperties(propFile);//加载配置文件
			}
		}
		return propData;
	}
	/**
	 * 加载消息内容模版properties文件   
	 * @author zhangkui @2014-09-01
	 * @param propertiesFile
	 * @return
	 */
	public static PropertiesConfiguration getSmsContentTemplateProperties(String propertiesFile){
		synchronized (propertiesFile) {
			  if(null == smsContentTemplateData)
				log.info("###初始化参数池，加载sms内容模版配置文件{}", propertiesFile);
				instance.loadSmsContentTemplateProperties(propertiesFile);//加载配置文件
			}
		return smsContentTemplateData;
	}
	public static XMLConfiguration getValidator(){
		synchronized (validatorFile.intern()) {
			if(null == validatorData){
				log.info("###初始化参数池，加载配置文件{}", validatorFile);
				instance.loadValidator(validatorFile);//加载配置文件
			}
		}
		return validatorData;
	}
	
	public boolean addLocalProperty(String key, String value){
		try {
			propData.addProperty(key, value);
			propData.save();
			return true;
		} catch (ConfigurationException e) {
			return false;
		}
	}
	
	
	/**
	 * 只修改本地值，不会同步给负载服务器
	 * @param key 参数key
	 * @param value 参数值
	 * @return 是否修改成功
	 */
	public boolean updateLocalProperty(String key, String value){
		try {
			propData.setProperty(key, value);
			propData.save();
			return true;
		} catch (ConfigurationException e) {
			return false;
		}
	}
		
	public List<Validator> listValidator() throws Exception{
		List<Validator> list = new ArrayList<Validator>();
		List<ConfigurationNode> services = validatorData.getRoot().getChildren();
		for (ConfigurationNode service : services) {
			Validator validator = nodeToValidator(service);
			list.add(validator);
		}
		return list;
	}
	
	public Validator getValidator(String id) throws Exception{
		Validator validator = new Validator();
		List<ConfigurationNode> services = validatorData.getRoot().getChildren();
		for (ConfigurationNode service : services) {
			 validator = nodeToValidator(service);
			if(id.equals(validator.getId())){
				return validator;
			}
		}
		return null;
	}
	
	public void	addValidator(Validator validator) throws Exception{
		validatorData.getRoot().addChild(validator);
		validatorData.save();
	} 
	
	public void	removeValidator(String id) throws Exception{
		List<ConfigurationNode> services = validatorData.getRoot().getChildren();
		for (int i = 0; i < services.size(); i++) {
			ConfigurationNode service = services.get(i);
			Validator validator = nodeToValidator(service);
			if(id.equals(validator.getId())){
				validatorData.getRoot().removeChild(service);
				i--;
			}
		}
		validatorData.save();
	} 
	
	public void	updateValidator(Validator validator) throws Exception{
		List<ConfigurationNode> services = validatorData.getRoot().getChildren();
		for (ConfigurationNode service : services) {
			if(validator.getId().equals(nodeToValidator(service).getId())){
				validatorData.getRoot().removeChild(service);
				validatorData.getRoot().addChild(validator);
				break;
			}
		}
		validatorData.save();
	} 
	
	private void loadProperties(String file){
		try {
			propData = new PropertiesConfiguration(file);
			propData.setAutoSave(false);
			propData.setEncoding("UTF-8");
		} catch (ConfigurationException e) {
			log.error("系统加载配置参数异常：{}",e);
		}
	}
	
	private void loadValidator(String file){
		try {
			validatorData = new XMLConfiguration(file);
			validatorData.setAutoSave(false);
			validatorData.setEncoding("UTF-8");
		} catch (ConfigurationException e) {
			log.error("系统加载配置参数异常：{}",e);
		}
	}
	
	private void loadSmsContentTemplateProperties(String file){
		try {
			smsContentTemplateData = new PropertiesConfiguration(file);
			smsContentTemplateData.setAutoSave(false);
			smsContentTemplateData.setEncoding("UTF-8");
		} catch (ConfigurationException e) {
			log.error("系统加载配置参数异常：{}",e);
		}
	}
	
	public Validator createValidator(String id, String title, String path) {
		Validator validator = new Validator();
		validator.setName("service");
		validator.setId(id);
		validator.setTitle(title);
		validator.setPath(path);
		return validator;
	}
	
	public Validator createValidator(String id, String title, String path, List<ValidRule> rules) {
		Validator validator = new Validator();
		validator.setName("service");
		validator.setId(id);
		validator.setTitle(title);
		validator.setPath(path);
		validator.setRules(rules);
		return validator;
	}
	
	public ValidRule createValidRule(String rulename, String title, Object value) {
		ValidRule rule = new ValidRule();
		rule.setName(rulename);
		rule.setTitle(title);
		rule.setValue(value);
		return rule;
	}
	
	public ValidRule createValidRule(String rulename, String title, String require, Object value) {
		ValidRule rule = new ValidRule();
		rule.setName(rulename);
		rule.setTitle(title);
		rule.setRequire(require);
		rule.setValue(value);
		return rule;
	}
	
	
	private Validator nodeToValidator(ConfigurationNode node) {
		Validator validator = new Validator();
		validator.setName(node.getName());
		for (ConfigurationNode attr : node.getAttributes()) {
			if("id".equals(attr.getName())){
				validator.setId((String) attr.getValue());
			}
			if("title".equals(attr.getName())){
				validator.setTitle((String) attr.getValue());
			}
			if("path".equals(attr.getName())){
				validator.setPath((String) attr.getValue());
			}
		}
		
		List<ValidRule> rules = new ArrayList<ValidRule>();
		for (ConfigurationNode child : node.getChildren()) {
			rules.add(nodeToValidRule(child));
		}
		validator.setRules(rules);
		return validator;
	}
	
	public ValidRule nodeToValidRule(ConfigurationNode node) {
		ValidRule rule = new ValidRule();
		rule.setName(node.getName());
		rule.setValue(node.getValue());
		for (ConfigurationNode attr : node.getAttributes()) {
			if("title".equals(attr.getName())){
				rule.setTitle((String) attr.getValue());
			}
			if("require".equals(attr.getName())){
				rule.setRequire( (String) attr.getValue());
			}
		}
		return rule;
	}
	
	public class Validator extends Node {
		private static final long serialVersionUID = 1L;
		private String id;
		private String title;
		private String path;
		private List<ValidRule> rules = new ArrayList<ValidRule>();

		public void addValidRule(ValidRule rule) {
			this.addChild(rule);
			this.rules.add(rule);
		}
		
		public void addAttribute(String name, Object value) {
			Node attr = new Node(name, value);
			this.addAttribute(attr);
		}
		
		public void setAttribute(String name, Object value) {
			boolean isAdd = true;
			for (ConfigurationNode attr : getAttributes()) {
				if(name.equals(attr.getName())){
					attr.setValue(value);
					isAdd = false;
					break;
				}
			}
			if(isAdd){
				addAttribute(name, value);
			}
		}
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.setAttribute("id", id);
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.setAttribute("title", title);
			this.title = title;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.setAttribute("path", path);
			this.path = path;
		}

		public List<ValidRule> getRules() {
			return rules;
		}

		public void setRules(List<ValidRule> rules) {
			for (ValidRule rule : rules) {
				this.removeChild(rule.getName());
				this.addChild(rule);
			}
			this.rules = rules;
		}
	}

	public class ValidRule extends Node {
		private static final long serialVersionUID = 1L;
		private String name;
		private String title;
		private String require;
		private Object value;

		public void addAttribute(String name, Object value) {
			Node attr = new Node(name, value);
			this.addAttribute(attr);
		}
		
		public void setAttribute(String name, Object value) {
			boolean isAdd = true;
			for (ConfigurationNode attr : getAttributes()) {
				if(name.equals(attr.getName())){
					attr.setValue(value);
					isAdd = false;
					break;
				}
			}
			if(isAdd){
				addAttribute(name, value);
			}
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			super.setName(name);
			this.name = name;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.setAttribute("title", title);
			this.title = title;
		}

		public String getRequire() {
			return require;
		}

		public void setRequire(String require) {
			this.setAttribute("require", require);
			this.require = require;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			super.setValue(value);
			this.value = value;
		}
		
	}

	public String getValidatorId(){
		String perfix = "valid";
		int seq = 1;
		List<ConfigurationNode> services = ParamUtil.getValidator().getRoot().getChildren();		
		for (ConfigurationNode service : services) {
			Validator validator = nodeToValidator(service);
			int cur = Integer.valueOf(validator.getId().substring(5));
			if(cur > seq) seq = cur;
		}
		seq += Math.round(Math.random()*5);
		if(seq < 10){
			return perfix + "00" + seq;
		}else if(seq < 100){
			return perfix + "0" + seq;
		}else{
			return perfix + seq;
		}
	} 
}
