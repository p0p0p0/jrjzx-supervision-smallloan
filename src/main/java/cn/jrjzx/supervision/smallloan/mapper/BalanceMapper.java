package cn.jrjzx.supervision.smallloan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;
import cn.jrjzx.supervision.smallloan.entity.Balance;

public interface BalanceMapper extends Mapper<Balance> {
	@Select("select date_month from balance where company_id=#{companyId} and flag=1 order by date_month")
	public List<String> getDateMonth(@Param("companyId") int companyId);
}
