package cn.jrjzx.supervision.smallloan.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 数据字典选项 
 *
 */
@Table(name = "data_option")
public class DataOption implements Serializable {
	private static final long serialVersionUID = -4157811641446535607L;
	@Id
	private int id;// 主键
	private String optionKey;// 选项key
	private String optionValue;// 选项value
	private int status;// 状态
	private int level;// 排序级别
	private int lexiconId;// 数据字典id

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOptionKey() {
		return optionKey;
	}

	public void setOptionKey(String optionKey) {
		this.optionKey = optionKey;
	}

	public String getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLexiconId() {
		return lexiconId;
	}

	public void setLexiconId(int lexiconId) {
		this.lexiconId = lexiconId;
	}

	@Override
	public String toString() {
		return "DataOption [id=" + id + ", optionKey=" + optionKey
				+ ", optionValue=" + optionValue + ", status=" + status
				+ ", level=" + level + ", lexiconId=" + lexiconId + "]";
	}

}
