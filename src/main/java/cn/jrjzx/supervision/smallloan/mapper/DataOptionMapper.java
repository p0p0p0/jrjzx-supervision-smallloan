package cn.jrjzx.supervision.smallloan.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;
import cn.jrjzx.supervision.smallloan.entity.DataOption;

public interface DataOptionMapper extends Mapper<DataOption> {
	@Select("select o.id,o.option_key,o.option_value,o.status,o.level,o.lexicon_id from data_option o  LEFT JOIN data_lexicon l on o.lexicon_id=l.id where l.code=#{code} and o.option_key=#{optionKey}")
	@Results(value = { @Result(id = true, property = "id", column = "id"),
			@Result(property = "optionKey", column = "option_key"),
			@Result(property = "optionValue", column = "option_value"),
			@Result(property = "status", column = "status"),
			@Result(property = "level", column = "level"),
			@Result(property = "lexiconId", column = "lexicon_id") })
	public DataOption getOptionByCodeAndKey(@Param("code") String code,
			@Param("optionKey") String key);
}
