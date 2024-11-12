package iuh.models;

import java.sql.Blob;

import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class DienThoai {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maDienThoai;
	@NotBlank(message = "Tên điện thoại không được để trống")
	private String tenDienThoai;

	@NotNull(message = "Năm sản xuất không được để trống")
	@NotNull(message = "Năm sản xuất là bắt buộc.")
	@Min(value = 1900, message = "Năm sản xuất phải từ 1900 trở lên.")
	@Max(value = 2024, message = "Năm sản xuất không được quá hiện tại.")
	private int namSanXuat;


	@NotBlank(message = "Cấu hình  không được để trống")
    @Size(max = 255, message = "Thông tin cấu hình không được quá 255 ký tự.")
	private String cauHinh;

    @Pattern(regexp = "([^\\s]+(\\.(?i)(png|jpg|jpeg))$)?", message = "Chỉ chấp nhận định dạng hình ảnh: png, jpg, jpeg.")
	private String hinhAnh;

	@ManyToOne
	@JoinColumn(name = "maNCC")
	private NhaCungCap nhaCungCap;

	@Override
	public String toString() {
		return "DienThoai [maDienThoai=" + maDienThoai + ", tenDienThoai=" + tenDienThoai + ", namSanXuat=" + namSanXuat
				+ ", cauHinh=" + cauHinh + ", hinhAnh=" + hinhAnh + "]";
	}

}
