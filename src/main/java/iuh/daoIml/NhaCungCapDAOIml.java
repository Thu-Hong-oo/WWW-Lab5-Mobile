package iuh.daoIml;

import java.util.List;

import iuh.dao.NhaCungCapDAO;
import iuh.models.NhaCungCap;
import jakarta.persistence.EntityManager;

public class NhaCungCapDAOIml implements NhaCungCapDAO {
	private EntityManager entityManager;

	public NhaCungCapDAOIml(EntityManager entityManager) {
		this.entityManager = entityManager;

	}

	@Override
	public List<NhaCungCap> findAll() {
		try {
			return entityManager.createQuery("From NhaCungCap", NhaCungCap.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public NhaCungCap findNCCByid(int id) {
		// TODO Auto-generated method stub
		try {
			return entityManager.find(NhaCungCap.class, id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
