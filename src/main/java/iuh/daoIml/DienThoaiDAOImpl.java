package iuh.daoIml;

import java.util.List;

import iuh.dao.DienThoaiDAO;
import iuh.models.DienThoai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DienThoaiDAOImpl implements DienThoaiDAO {
	private EntityManager entityManager;

	public DienThoaiDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DienThoai> findAllDienThoaiByNCC(int id) {
		try {
			return entityManager
					.createQuery("SELECT d FROM DienThoai d WHERE d.nhaCungCap.maNCC = :maNCC", DienThoai.class)
					.setParameter("maNCC", id).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("null")
	@Override
	public DienThoai save(DienThoai dienThoai) {
		EntityTransaction entityTransaction = null;
		try {
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.persist(dienThoai);
			entityTransaction.commit();
			return dienThoai;

		} catch (Exception e) {
			if (entityTransaction != null && entityTransaction.isActive())
				entityTransaction.rollback();
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<DienThoai> findAllDienThoai() {
		try {
			return entityManager.createQuery("select d from DienThoai d join fetch d.nhaCungCap ", DienThoai.class)
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DienThoai findAllDienThoaiById(int id) {
		try {
			return entityManager.find(DienThoai.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("null")
	@Override
	public DienThoai update(DienThoai dienThoai) {
		EntityTransaction entityTransaction = null;
		try {
			entityTransaction = entityManager.getTransaction(); // Khởi tạo giao dịch
			entityTransaction.begin();
			System.out.println("Updating DienThoai: " + dienThoai); // Ghi log
			entityManager.merge(dienThoai); // Sử dụng merge để cập nhật
			entityTransaction.commit(); // Cam kết giao dịch
			return dienThoai;
		} catch (Exception e) {
			if (entityTransaction != null && entityTransaction.isActive()) {
				entityTransaction.rollback(); // Hoàn tác giao dịch nếu có lỗi
			}
			e.printStackTrace(); // In lỗi ra console
		}
		return null;
	}

}
