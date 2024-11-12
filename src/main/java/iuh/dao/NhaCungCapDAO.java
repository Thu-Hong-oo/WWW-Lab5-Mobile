package iuh.dao;

import java.util.List;

import iuh.models.NhaCungCap;

public interface NhaCungCapDAO {
	public List<NhaCungCap> findAll();
	public NhaCungCap findNCCByid(int id);

}
