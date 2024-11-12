package iuh.dao;

import java.util.List;

import iuh.models.DienThoai;

public interface DienThoaiDAO {
	public List<DienThoai> findAllDienThoai();
	public List<DienThoai> findAllDienThoaiByNCC(int id);
	public DienThoai findAllDienThoaiById(int id);
	public DienThoai save(DienThoai dienThoai);
	public DienThoai update(DienThoai dienThoai);

}
