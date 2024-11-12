package iuh.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhaCungCap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maNCC;
	private String tenNCC;
	private String diaChi;
	private String soDienThoai;

	@OneToMany(mappedBy = "nhaCungCap", fetch = FetchType.EAGER)
	List<DienThoai> dienThoais;

	@Override
	public String toString() {
		return "NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChi=" + diaChi + ", soDienThoai="
				+ soDienThoai + "]";
	}
	
	
}
