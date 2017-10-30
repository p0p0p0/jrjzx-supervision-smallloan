package cn.jrjzx.supervision.smallloan.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 数据字典 
 *
 */
@Table(name = "data_lexicon")
public class DataLexicon implements Serializable {
	private static final long serialVersionUID = 1138023020010729843L;
	@Id
	private int id;
	private String name;// 数据字典名称
	private String code;// 数据字典代码
	private String description;// 数据字典描述
	@Transient
	private List<DataOption> dataOptions;// 数据字典选项

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<DataOption> getDataOptions() {
		return dataOptions;
	}

	public void setDataOptions(List<DataOption> dataOptions) {
		this.dataOptions = dataOptions;
	}

	@Override
	public String toString() {
		return "DataLexicon [id=" + id + ", name=" + name + ", code=" + code
				+ ", description=" + description + "]";
	}

}
