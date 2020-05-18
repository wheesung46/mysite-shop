package com.bit.myshop.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit.myshop.vo.ProductVo;

@Repository
public class ProductDao {
	@Autowired
	private SqlSession sqlSession;
	
	public List<ProductVo> getList(){
		return sqlSession.selectList("product.getList");
	}
	
	public int insert(ProductVo productVo) {
		return sqlSession.insert("product.insert", productVo);
	}
	
	public int delete(ProductVo productVo) {
		return sqlSession.delete("product.delete", productVo);
	}
	
	public ProductVo get(Long no) {
		return sqlSession.selectOne("product.getByNo", no);
	}
	
	public void update(ProductVo vo) {
		sqlSession.update("product.update", vo);
	}
}
