package com.etc.ui.desktop.BaoCao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.SystemUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.common.IOUtil;

import com.etc.dal.BaoCaoTongHopDAO;
import com.etc.dal.DiaDanhHanhChinhDAO;
import com.etc.dal.DonViCanhsatGtDAO;
import com.etc.dal.HanhViViPhamDAO;
import com.etc.dal.HinhThucXuPhatBoSungDAO;
import com.etc.dal.NghiDinhCpDAO;
import com.etc.dal.NhomHvvpDAO;
import com.etc.dal.NoicapGiaytoDAO;
import com.etc.dal.QuocLoTuyenduongDAO;
import com.etc.dal.ViewBaocaothBb43DAO;
import com.etc.dal.ViewBaocaothBb50DAO;
import com.etc.dal.ViewBaocaothBb60DAO;
import com.etc.dal.ViewBaocaothQd01DAO;
import com.etc.dal.ViewBaocaothQd02DAO;
import com.etc.dal.ViewBaocaothQd18DAO;
import com.etc.dal.ViewBaocaothQd20DAO;
import com.etc.dal.auth.AuthUserDAO;
import com.etc.entities.BaoCaoTongHop;
import com.etc.entities.BaoCaoTongHop_;
import com.etc.entities.ChucVu;
import com.etc.entities.ChucVu_;
import com.etc.entities.DiaDanhHanhChinh;
import com.etc.entities.DiaDanhHanhChinh_;
import com.etc.entities.DonViCanhsatGt;
import com.etc.entities.HangGplx;
import com.etc.entities.HangGplx_;
import com.etc.entities.HanhViViPham;
import com.etc.entities.HanhViViPham_;
import com.etc.entities.HinhThucXuPhatBoSung;
import com.etc.entities.HinhThucXuPhatBoSung_;
import com.etc.entities.HinhThucXuPhatVphc;
import com.etc.entities.HinhThucXuPhatVphc_;
import com.etc.entities.KhoBacNganHang;
import com.etc.entities.LoaiPhuongTien;
import com.etc.entities.LoaiPhuongTien_;
import com.etc.entities.NgheNghiep;
import com.etc.entities.NgheNghiep_;
import com.etc.entities.NghiDinhCp;
import com.etc.entities.NghiDinhCp_;
import com.etc.entities.NhomHvvp;
import com.etc.entities.NhomHvvp_;
import com.etc.entities.NoicapGiayto;
import com.etc.entities.NoicapGiayto_;
import com.etc.entities.QuocLoTuyenduong;
import com.etc.entities.QuocLoTuyenduong_;
import com.etc.entities.ViewBaocaothBb43;
import com.etc.entities.ViewBaocaothBb50;
import com.etc.entities.ViewBaocaothBb60;
import com.etc.entities.ViewBaocaothQd01;
import com.etc.entities.ViewBaocaothQd02;
import com.etc.entities.ViewBaocaothQd18;
import com.etc.entities.ViewBaocaothQd20;
import com.etc.entities.auth.AuthUser;
import com.etc.entities.auth.AuthUser_;
import com.etc.security.common.authentication.ui.Authentication;
import com.etc.ui.desktop.CreateID;
import com.etc.utils.FormatUtils;
import com.lowagie.text.Font;
import com.vaadin.addon.tableexport.ExcelExport;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.xdev.dal.DAOs;
import com.xdev.ui.XdevBrowserFrame;
import com.xdev.ui.XdevButton;
import com.xdev.ui.XdevCheckBox;
import com.xdev.ui.XdevGridLayout;
import com.xdev.ui.XdevHorizontalLayout;
import com.xdev.ui.XdevLabel;
import com.xdev.ui.XdevPopupDateField;
import com.xdev.ui.XdevTabSheet;
import com.xdev.ui.XdevTextField;
import com.xdev.ui.XdevTreeTable;
import com.xdev.ui.XdevView;
import com.xdev.ui.entitycomponent.combobox.XdevComboBox;
import com.xdev.ui.entitycomponent.table.XdevTable;

public class Bao_Cao_Tong_Hop extends XdevView {
	protected FileDownloader fileDownloader;
	private final HashMap<Long, ArrayList<DonViCanhsatGt>> map = new HashMap<>();
	private final HashSet<Long> resourceSet = new HashSet<>();
	private final HashSet<Long> resourceDv = new HashSet<>();
	private final HashSet<Long> resourceHv = new HashSet<>();
	public String danhSachBCTH = "";
	boolean check = false;

	/**
	 * 
	 */
	public Bao_Cao_Tong_Hop() {
		super();
		this.initUI();
		this.check = false;
		this.btnbaocaoTongHop.setEnabled(false);
		this.btnTheoTieuChi.setEnabled(false);
		populateTreeTable();
		generateCheckBoxColumn();
		this.treeTable.setVisibleColumns("Lựa chọn", "Đơn vị");
		this.treeTable.setColumnWidth("Lựa chọn", 90);
		this.cmbLinhVuc.addItems(new Object[] { "Tất cả", "Đường sắt", "Đường bộ", "Đường thủy" });
		this.cmbLinhVuc.setValue("Tất cả");
		this.cmbLoaiGiayTo.addItems(new Object[] { "Tất cả", "GPLX", "Kiểm định", "Đăng ký", "Phương tiện", "Khác" });
		this.cmbLoaiGiayTo.setValue("Tất cả");
		this.cmbToChuc.addItems(new Object[] { "Tất cả", "Cá nhân", "Tổ chức" });
		this.cmbToChuc.setValue("Tất cả");
		this.cmbHinhThucNP.addItems(new Object[] { "Tất cả", "Qua DVC", "Trực tiếp" });
		this.cmbHinhThucNP.setValue("Tất cả");
		this.cmbNhomHv.addItems(new Object[] { "Tất cả", "Vi phạm về nồng độ cồn khi điều khiển phương tiện",
				"Người điều khiển phương tiện trên đường mà trong cơ thể có chất ma túy",
				"Điều khiển phương tiện lạng lách đánh võng", "Đi sai làn đường, phần đường", "Tránh vượt sai quy định",
				"Dừng đỗ sai quy định", "Vi phạm quy định về đội mũ bảo hiểm", "Chở quá trọng tải hàng hóa",
				"Chở quá số người quy định",
				"Không chấp hành tín hiệu của đèn giao thông, hiệu lệnh và hướng dẫn của người điều khiển giao thông hoặc người kiểm soát giao thông",
				"Đi vào đường cấm, đi ngược chiều đường một chiều", "Chạy quá tốc độ cho phép",
				"Vi phạm quy định về niên hạn sử dụng của phương tiện",
				"Vi phạm quy định về đóng mới, hoán cải, sửa chữa phương tiện", "Vi phạm quy định về GPLX",
				"Vi phạm quy định xếp dỡ hàng hóa trên phương tiện", "Vi phạm quy định về vận chuyển người, hành khách",
				"Hành vi khác" });
		this.cmbNhomHv.setValue("Tất cả");
		this.cmbHvvp.setContainerDataSource(HanhViViPham.class, DAOs.get(HanhViViPhamDAO.class).GetRfTable(0, 0, 0));
		if (Authentication.getAuthUser().getDonViCanhsatGt().getDonViResourceId() == 1) {
			if (Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId() == 1) {
				Bao_Cao_Tong_Hop.this.danhSachBCTH = "";
			} else if (Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId() == 3) {
				final DonViCanhsatGtDAO DvDAO = new DonViCanhsatGtDAO();
				final List<DonViCanhsatGt> listDvPhong = DvDAO.getDVResourceBYCAP12(
						Authentication.getAuthUser().getDonViCanhsatGt().getId(),
						Authentication.getAuthUser().getDonViCanhsatGt().getId(),
						Authentication.getAuthUser().getDonViCanhsatGt().getDonViResourceId());
				if (listDvPhong.size() > 0) {
					for (int i = 0; i < listDvPhong.size(); i++) {
						Bao_Cao_Tong_Hop.this.resourceDv.add(listDvPhong.get(i).getId());
						Bao_Cao_Tong_Hop.this.danhSachBCTH = Bao_Cao_Tong_Hop.this.resourceDv.toString().substring(1,
								Bao_Cao_Tong_Hop.this.resourceDv.toString().length() - 1);
					}
				}
			} else if (Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId() == 4) {
				List<DonViCanhsatGt> ListDv;
				final DonViCanhsatGtDAO DvDAO = new DonViCanhsatGtDAO();
				ListDv = DvDAO.DanhSachTheoDVi_taoMoi(Authentication.getAuthUser().getDonViCanhsatGt().getId());
				if (ListDv.size() > 0) {
					for (int i = 0; i < ListDv.size(); i++) {
						Bao_Cao_Tong_Hop.this.resourceDv.add(ListDv.get(i).getId());
						Bao_Cao_Tong_Hop.this.danhSachBCTH = Bao_Cao_Tong_Hop.this.resourceDv.toString().substring(1,
								Bao_Cao_Tong_Hop.this.resourceDv.toString().length() - 1);
					}
				}
			}
		} else {
			if (Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId() == 2) {
				final List<DonViCanhsatGt> list = new DonViCanhsatGtDAO()
						.getListDiaPhuong(Authentication.getAuthUser().getDonViCanhsatGt().getDonViResourceId());
				if (list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						Bao_Cao_Tong_Hop.this.resourceDv.add(list.get(i).getId());
						Bao_Cao_Tong_Hop.this.danhSachBCTH = Bao_Cao_Tong_Hop.this.resourceDv.toString().substring(1,

								Bao_Cao_Tong_Hop.this.resourceDv.toString().length() - 1);
					}
				}
			} else if (Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId() == 3) {
				final DonViCanhsatGtDAO DvDAO = new DonViCanhsatGtDAO();
				final List<DonViCanhsatGt> listDvPhong = DvDAO.getDVResourceBYCAP12(
						Authentication.getAuthUser().getDonViCanhsatGt().getId(),
						Authentication.getAuthUser().getDonViCanhsatGt().getId(),
						Authentication.getAuthUser().getDonViCanhsatGt().getDonViResourceId());
				if (listDvPhong.size() > 0) {
					for (int i = 0; i < listDvPhong.size(); i++) {
						Bao_Cao_Tong_Hop.this.resourceDv.add(listDvPhong.get(i).getId());
						Bao_Cao_Tong_Hop.this.danhSachBCTH = Bao_Cao_Tong_Hop.this.resourceDv.toString().substring(1,
								Bao_Cao_Tong_Hop.this.resourceDv.toString().length() - 1);
					}
				}
			} else if (Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId() == 4) {
				List<DonViCanhsatGt> ListDv;
				final DonViCanhsatGtDAO DvDAO = new DonViCanhsatGtDAO();
				ListDv = DvDAO.DanhSachTheoDVi_taoMoi(Authentication.getAuthUser().getDonViCanhsatGt().getId());
				if (ListDv.size() > 0) {
					for (int i = 0; i < ListDv.size(); i++) {
						Bao_Cao_Tong_Hop.this.resourceDv.add(ListDv.get(i).getId());
						Bao_Cao_Tong_Hop.this.danhSachBCTH = Bao_Cao_Tong_Hop.this.resourceDv.toString().substring(1,
								Bao_Cao_Tong_Hop.this.resourceDv.toString().length() - 1);
					}
				}
			}
		}
		if (Authentication.getAuthUser().getDonViCanhsatGt().getDonViResourceId() == 1) {
			if (Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId() == 1) {
				this.cmbnhomHvvp.setContainerDataSource(NhomHvvp.class, DAOs.get(NhomHvvpDAO.class).FindNhom());
			} else {
				this.cmbnhomHvvp.setContainerDataSource(NhomHvvp.class, DAOs.get(NhomHvvpDAO.class)
						.getListByDvId(Authentication.getAuthUser().getDonViCanhsatGt().getId()));
			}
		} else {
			this.cmbnhomHvvp.setContainerDataSource(NhomHvvp.class, DAOs.get(NhomHvvpDAO.class)
					.getListByDvId(Authentication.getAuthUser().getDonViCanhsatGt().getId()));
		}
		this.cmbTrangThaiXl.addItems(new Object[] { "Tất cả", "Đang tạm giữ", "Đã trả" });
		this.cmbTrangThaiXl.setValue("Tất cả");
		this.cmbLoaiBb.addItems(new Object[] { "Tất cả", "QĐ XPVPHCKLBB (01)", "BB VPHC (43)", "QĐ XPVPHC (02)",
				"QĐ TGTV (18)", "QĐ TLTV (20)", "BB TGTV (50)", "BB TLTV (60)" });
		this.cmbLoaiBb.setValue("Tất cả");
		if (Authentication.getAuthUser().getDonViCanhsatGt().getDonViResourceId() == 1) {
			this.cmbTinh.setContainerDataSource(DiaDanhHanhChinh.class,
					DAOs.get(DiaDanhHanhChinhDAO.class).ListDiaDanhCap1());
			this.cmbQuocLo.setContainerDataSource(QuocLoTuyenduong.class,
					DAOs.get(QuocLoTuyenduongDAO.class).ListPoolByIdQuocLo());
			this.cmbTuyenDuong.setContainerDataSource(QuocLoTuyenduong.class,
					DAOs.get(QuocLoTuyenduongDAO.class).ListPoolByIdTuyenduong());
		} else {
			this.cmbTinh.setContainerDataSource(DiaDanhHanhChinh.class,
					DAOs.get(DiaDanhHanhChinhDAO.class).getListbyId2(
							Authentication.getAuthUser().getDonViCanhsatGt().getDiaDanhHanhChinh().getDdResourceId()));
			this.cmbQuocLo.setContainerDataSource(QuocLoTuyenduong.class, DAOs.get(QuocLoTuyenduongDAO.class)
					.getListByIdDv(Authentication.getAuthUser().getDonViCanhsatGt().getId(), 1));
			this.cmbTuyenDuong.setContainerDataSource(QuocLoTuyenduong.class, DAOs.get(QuocLoTuyenduongDAO.class)
					.getListByIdDv(Authentication.getAuthUser().getDonViCanhsatGt().getId(), 2));
		}
		// this.cmbTinh.setContainerDataSource(DiaDanhHanhChinh.class,
		// DAOs.get(DiaDanhHanhChinhDAO.class).ListDiaDanhCap1());
		this.cmbQuanHuyen.setContainerDataSource(DiaDanhHanhChinh.class,
				DAOs.get(DiaDanhHanhChinhDAO.class).getListCapTren(0, "2"));
		this.cmbPhuongXa.setContainerDataSource(DiaDanhHanhChinh.class,
				DAOs.get(DiaDanhHanhChinhDAO.class).getListCapDuoi(0, "3"));
		this.cmbCanBoLap.setContainerDataSource(AuthUser.class,
				DAOs.get(AuthUserDAO.class).DSachTao(
						Authentication.getAuthUser().getDonViCanhsatGt().getDonViResourceId(),
						Authentication.getAuthUser().getDonViCanhsatGt().getId(),
						Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId(),
						Authentication.getAuthUser().getId()));
		System.out.println("//");

		// ddlNam
		int namBatDau = 2019;
		int i = 0;
		do {

			namBatDau++;
			this.cmbThang.addItem(String.valueOf(namBatDau));
			this.cmbQuy.addItem(String.valueOf(namBatDau));
			this.cmbQuy2.addItem(String.valueOf(namBatDau));
			i++;
		} while (i <= 100);

		int j = 0;
		String thang = "";
		do {
			thang = "Tháng " + (j + 1);
			this.comboBox.addItem(thang);
			j++;
		} while (j <= 11);

		final Calendar calendar = Calendar.getInstance();
		final SimpleDateFormat ngay = new SimpleDateFormat("dd/MM/yyyy");
		final String thangHienTai = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		final String namHienTai = String.valueOf(calendar.get(Calendar.YEAR));

		final Collection<?> itemIdsQG = this.cmbThang.getItemIds();
		final List<String> list = (List) itemIdsQG;

		final List<String> listThang = (List) this.comboBox.getItemIds();
		this.cmbThang.setValue(
				list.stream().filter(n -> n.equalsIgnoreCase(namHienTai)).collect(Collectors.toList()).get(0));
		this.cmbQuy.setValue(
				list.stream().filter(n -> n.equalsIgnoreCase(namHienTai)).collect(Collectors.toList()).get(0));
		this.cmbQuy2.setValue(
				list.stream().filter(n -> n.equalsIgnoreCase(namHienTai)).collect(Collectors.toList()).get(0));
		this.comboBox
				.setValue(listThang.stream().filter(n -> n.contains(thangHienTai)).collect(Collectors.toList()).get(0));

		int x = 0;
		Date thu = null;
		Date fri = null;
		Date sat = null;
		Date sun = null;
		final Date ngayHienTai = new Date();
		while (true) {
			if (x == 0) {
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
				thu = calendar.getTime();
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
				fri = calendar.getTime();
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
				sat = calendar.getTime();
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				sun = calendar.getTime();
			} else {
				thu = null;
				fri = null;
				sat = null;
				sun = null;
			}
			Date sunday = new Date();
			Date monday = new Date();
			if (thu != null) {
				if (ngay.format(thu).equals(ngay.format(ngayHienTai))
						|| ngay.format(fri).equals(ngay.format(ngayHienTai))
						|| ngay.format(sat).equals(ngay.format(ngayHienTai))
						|| ngay.format(sun).equals(ngay.format(ngayHienTai))) {
					calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
					monday = calendar.getTime();
					calendar.add(Calendar.DATE, 7);
					calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
					sunday = calendar.getTime();
					this.cmbTuan.addItem(ngay.format(monday) + " đến " + ngay.format(sunday));
					calendar.add(Calendar.DATE, -8);
				} else {
					calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
					sunday = calendar.getTime();
					calendar.add(Calendar.DATE, -7);
					calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
					monday = calendar.getTime();
					this.cmbTuan.addItem(ngay.format(monday) + " đến " + ngay.format(sunday));
				}
			} else {
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
				sunday = calendar.getTime();
				calendar.add(Calendar.DATE, -7);
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
				monday = calendar.getTime();
				this.cmbTuan.addItem(ngay.format(monday) + " đến " + ngay.format(sunday));
			}
			if (x == 0) {
				this.cmbTuan.setValue(ngay.format(monday) + " đến " + ngay.format(sunday));
			}
			x++;
			if (x == 100) {
				break;
			}
		}

		final String quy = "";
		int a = 0;
		do {
			this.cmbTenQuy.addItem("Quý " + (a + 1));
			a++;
		} while (a <= 3);
		this.cmbTenQuy.addItem("Quý 1+2");
		this.cmbTenQuy.addItem("Quý 3+4");

		final int thangHienTai1 = Integer.parseInt(thangHienTai);
		if (thangHienTai1 == 1 || thangHienTai1 == 2 || thangHienTai1 == 3) {
			this.cmbTenQuy.setValue("Quý 1");
		} else if (thangHienTai1 == 4 || thangHienTai1 == 5 || thangHienTai1 == 6) {
			this.cmbTenQuy.setValue("Quý 2");
		} else if (thangHienTai1 == 7 || thangHienTai1 == 8 || thangHienTai1 == 9) {
			this.cmbTenQuy.setValue("Quý 3");
		} else {
			this.cmbTenQuy.setValue("Quý 4");
		}

		final Date date = new Date();
		this.pdfNgay.setValue(date);
		
		
		
		Bao_Cao_Tong_Hop.this.btnbaocaoTongHop.setEnabled(false);
		Bao_Cao_Tong_Hop.this.btnTheoTieuChi.setEnabled(false);
		Bao_Cao_Tong_Hop.this.btnWeeklyReport3.setEnabled(false);
		Bao_Cao_Tong_Hop.this.btnWeeklyReport.setEnabled(false);
		Bao_Cao_Tong_Hop.this.btnMonthlyReport.setEnabled(false);
		Bao_Cao_Tong_Hop.this.btnQuarterReport.setEnabled(false);
		Bao_Cao_Tong_Hop.this.btnYearlyReport.setEnabled(false);
	}

	public String listDanhSachBCTH = "";
	public String todateBCTH = "";
	public String fromdateBCTH = "";
	public String nhomhvBCTH = "";
	public String nhomhvKhacBCTH = "";
	public String tuNgayBCTH = "";
	public String denNgayBCTH = "";
	public int ToChucBCTH = -1;
	public int LinhVucBCTH = 0;
	public long loaiBBBCTH = 0;
	public String tenNvpBCTH = "";
	public String DiaChiNvpBCTH = "";
	public String ngheNghiepNvpBCTH = "";
	public String NoiCapTvBCTH = "";
	public String SoGiayToBCTH = "";
	public String HangGPLXBCTH = "";
	public String BKSBCTH = "";
	public String soBBBCTH = "";
	public String CanBoBCTH = "";
	public long tuoiBCTH = 0;
	public long tuoiDenBCTH = 0;
	public String thoiHanTuBCTH = "";
	public String thoiHanDenBCTH = "";
	public String tangVatBCTH = "";
	public String loaiPhuongTienBCTH = "";
	public int TrangThaiXuLyBCTH = -1;
	public String hinhThucPhatBCTH = "";
	public String TienPhatTuBCTH = "";
	public String TienPhatDenBCTH = "";
	public String HinhThucNopPhatBCTH = "";
	public String NpTrucTuyenBCTH = "";
	public String xpbsBCTH = "";
	public String tuocTuNgayBCTH = "";
	public String tuocDenNgayBCTH = "";
	public String nghiDinhBCTH = "";
	public long hanhViVPBCTH = 0;
	public String tuNamBCTH = "";
	public String denNamBCTH = "";
	public String chucVuBCTH = "";
	public String khobacBCTH = "";
	public String TinhBCTH = "";
	public String QuanBCTH = "";
	public String XaBCTH = "";
	public String QuocLoBCTH = "";
	public String TuyenDuongBCTH = "";
	public String diaBanVp = "";
	public long checkHvvp = 0;
	boolean checkId = true;
	
	private void generateCheckBoxColumn() {
		final Set<Integer> list = new HashSet<>();
		final Map<Integer, CheckBox> map = new HashMap<>();
		final Map<Integer, CheckBox> idDV = new HashMap<>();
		final Map<Integer, CheckBox> idDV2 = new HashMap<>();
		
		this.treeTable.addGeneratedColumn("Lựa chọn", new ColumnGenerator() {
			@Override
			public Component generateCell(final Table source, final Object itemId, final Object columnId) {

				if (columnId.toString().equals("Lựa chọn")) {

					final CheckBox checkBox = new CheckBox();
					// Bao_Cao_Tong_Hop.this.checkBox1 = new CheckBox();
					checkBox.setImmediate(true);
					checkBox.setId(itemId.toString());
					final DonViCanhsatGt dv = new DonViCanhsatGtDAO().find(Long.parseLong(itemId.toString()));

					final Long capDonVi = Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId();
					if (capDonVi == 1 || capDonVi == 2) {
						if (dv.getCapDonVi().getId() == 1 || dv.getCapDonVi().getId() == 2) {
							list.add(Integer.parseInt(itemId.toString()));
							map.put(Integer.parseInt(itemId.toString()), checkBox);
						}
					} else if (capDonVi == 3) {
						if (dv.getCapDonVi().getId() == 3) {
							list.add(Integer.parseInt(itemId.toString()));
							map.put(Integer.parseInt(itemId.toString()), checkBox);
						}
					} else if (capDonVi == 4) {
						if (dv.getCapDonVi().getId() == 4) {
							list.add(Integer.parseInt(itemId.toString()));
							map.put(Integer.parseInt(itemId.toString()), checkBox);
						}
					}

					if (Bao_Cao_Tong_Hop.this.resourceSet.contains(itemId)) {
						checkBox.setValue(true);
					} else {
						checkBox.setValue(false);
					}

					Bao_Cao_Tong_Hop.this.checkBox.addValueChangeListener(new ValueChangeListener() {

						@Override
						public void valueChange(final ValueChangeEvent event) {
							// TODO Auto-generated method stub
							if (Bao_Cao_Tong_Hop.this.checkBox.getValue() == true) {
								Bao_Cao_Tong_Hop.this.checkId = true;
								for (final Map.Entry<Integer, CheckBox> entry : map.entrySet()) {
									entry.getValue().setValue(true);
								}
								if(idDV2.size() > 0){
									for (final Map.Entry<Integer, CheckBox> entry : idDV2.entrySet()) {
										entry.getValue().setValue(true);
								}
									idDV2.clear();
								}

							} else {
								if(Bao_Cao_Tong_Hop.this.checkId == true){
								for (final Map.Entry<Integer, CheckBox> entry : map.entrySet()) {
									entry.getValue().setValue(false);
								}
								}
							}
						}
					});

					// what happens when check-box is clicked
					checkBox.addValueChangeListener(new ValueChangeListener() {

						@Override
						public void valueChange(final ValueChangeEvent event) {
							// check if it is a group row or a student row
							final Long resourceID = (Long) itemId;
							Long ParentID = (long) 0;
							// its a group row
							// set the preselected value of the check box
							if (checkBox.getValue() == true) {
								Bao_Cao_Tong_Hop.this.btnbaocaoTongHop.setEnabled(true);
								Bao_Cao_Tong_Hop.this.btnTheoTieuChi.setEnabled(true);
								
								
								Bao_Cao_Tong_Hop.this.btnWeeklyReport3.setEnabled(true);
								Bao_Cao_Tong_Hop.this.btnWeeklyReport.setEnabled(true);
								Bao_Cao_Tong_Hop.this.btnMonthlyReport.setEnabled(true);
								Bao_Cao_Tong_Hop.this.btnQuarterReport.setEnabled(true);
								Bao_Cao_Tong_Hop.this.btnYearlyReport.setEnabled(true);
								Bao_Cao_Tong_Hop.this.resourceSet.add(resourceID);
								
								if(idDV.size() > 0){
									for (final Map.Entry<Integer, CheckBox> entry : idDV.entrySet()) {
										if(entry.getKey() == resourceID.intValue()){
											idDV.remove(entry.getKey());
											break;
										}
										
								}
									if(idDV.size() ==  0){
										Bao_Cao_Tong_Hop.this.checkBox.setValue(true);
									}
								}
								
								
								
							
								try {
									DonViCanhsatGt res = new DonViCanhsatGt();
									res = DAOs.get(DonViCanhsatGtDAO.class).find(resourceID);
									if (res.getCapDonVi().getId() == 1 || res.getCapDonVi().getId() == 2) {
										ParentID = res.getDonViResourceId();
										final List<DonViCanhsatGt> danhSachDv = new DonViCanhsatGtDAO()
												.getListAddDonvi1("1, 2, 3 ,4", ParentID, ParentID);
										if (danhSachDv.size() > 0) {
											for (int i = 0; i < danhSachDv.size(); i++) {
												Bao_Cao_Tong_Hop.this.resourceSet.add(danhSachDv.get(i).getId());
											}
										}
									} else if (res.getCapDonVi().getId() == 3) {
										ParentID = res.getId();
										final List<DonViCanhsatGt> danhSachDv = new DonViCanhsatGtDAO()
												.ChonDs1("1, 2, 3 ,4", ParentID, ParentID);
										if (danhSachDv.size() > 0) {
											for (int i = 0; i < danhSachDv.size(); i++) {
												Bao_Cao_Tong_Hop.this.resourceSet.add(danhSachDv.get(i).getId());
											}
										}
									}

								} catch (final Exception e) {
									// TODO: handle exception
								}

								//
							} else {
								
								DonViCanhsatGt res = new DonViCanhsatGt();
								res = DAOs.get(DonViCanhsatGtDAO.class).find(resourceID);
								
								idDV.put(Integer.parseInt(resourceID.toString()), checkBox);
								idDV2.put(Integer.parseInt(resourceID.toString()), checkBox);
								
								
								if (res.getCapDonVi().getId() == 1 || res.getCapDonVi().getId() == 2) {
									ParentID = res.getDonViResourceId();
									final List<DonViCanhsatGt> danhSachDv = new DonViCanhsatGtDAO()
											.getListAddDonvi1("1, 2, 3 ,4", ParentID, ParentID);
									if (danhSachDv.size() > 0) {
										for (int i = 0; i < danhSachDv.size(); i++) {
											Bao_Cao_Tong_Hop.this.resourceSet.remove(danhSachDv.get(i).getId());
										}
									}
								} else if (res.getCapDonVi().getId() == 3) {
									ParentID = res.getId();
									final List<DonViCanhsatGt> danhSachDv = new DonViCanhsatGtDAO()
											.ChonDs1("1, 2, 3 ,4", ParentID, ParentID);
									if (danhSachDv.size() > 0) {
										for (int i = 0; i < danhSachDv.size(); i++) {
											Bao_Cao_Tong_Hop.this.resourceSet.remove(danhSachDv.get(i).getId());
										}
									}
								}
								Bao_Cao_Tong_Hop.this.resourceSet.remove(resourceID);
//								System.out.println(Bao_Cao_Tong_Hop.this.resourceSet.size());
								if(Bao_Cao_Tong_Hop.this.resourceSet.size()  < 1){
								Bao_Cao_Tong_Hop.this.btnbaocaoTongHop.setEnabled(false);
								Bao_Cao_Tong_Hop.this.btnTheoTieuChi.setEnabled(false);
								Bao_Cao_Tong_Hop.this.btnWeeklyReport3.setEnabled(false);
								Bao_Cao_Tong_Hop.this.btnWeeklyReport.setEnabled(false);
								Bao_Cao_Tong_Hop.this.btnMonthlyReport.setEnabled(false);
								Bao_Cao_Tong_Hop.this.btnQuarterReport.setEnabled(false);
								Bao_Cao_Tong_Hop.this.btnYearlyReport.setEnabled(false);
								Bao_Cao_Tong_Hop.this.checkBox.setValue(false);
								}
								else{
									Bao_Cao_Tong_Hop.this.checkId = false;
									Bao_Cao_Tong_Hop.this.checkBox.setValue(false);
								}
							}
							// set the values of the child nodes
							setChildNotes(resourceID);
							Bao_Cao_Tong_Hop.this.treeTable.setCollapsed(itemId, true);
							Bao_Cao_Tong_Hop.this.treeTable.setCollapsed(itemId, false);
							if (Bao_Cao_Tong_Hop.this.resourceSet.isEmpty() == false) {
								Bao_Cao_Tong_Hop.this.danhSachBCTH = Bao_Cao_Tong_Hop.this.resourceSet.toString()
										.substring(1, Bao_Cao_Tong_Hop.this.resourceSet.toString().length() - 1);
							} else {
								if (Authentication.getAuthUser().getDonViCanhsatGt().getDonViResourceId() == 1) {
									if (Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId() == 1) {
										Bao_Cao_Tong_Hop.this.danhSachBCTH = "";
									} else if (Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi()
											.getId() == 3) {
										final DonViCanhsatGtDAO DvDAO = new DonViCanhsatGtDAO();
										final List<DonViCanhsatGt> listDvPhong = DvDAO.getDVResourceBYCAP12(
												Authentication.getAuthUser().getDonViCanhsatGt().getId(),
												Authentication.getAuthUser().getDonViCanhsatGt().getId(),
												Authentication.getAuthUser().getDonViCanhsatGt().getDonViResourceId());
										if (listDvPhong.size() > 0) {
											for (int i = 0; i < listDvPhong.size(); i++) {
												Bao_Cao_Tong_Hop.this.resourceDv.add(listDvPhong.get(i).getId());
												Bao_Cao_Tong_Hop.this.danhSachBCTH = Bao_Cao_Tong_Hop.this.resourceDv
														.toString().substring(1,
																Bao_Cao_Tong_Hop.this.resourceDv.toString().length()
																		- 1);
											}
										}
									} else if (Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi()
											.getId() == 4) {
										List<DonViCanhsatGt> ListDv;
										final DonViCanhsatGtDAO DvDAO = new DonViCanhsatGtDAO();

										ListDv = DvDAO.DanhSachTheoDVi_taoMoi(
												Authentication.getAuthUser().getDonViCanhsatGt().getId());

										if (ListDv.size() > 0) {
											for (int i = 0; i < ListDv.size(); i++) {
												Bao_Cao_Tong_Hop.this.resourceDv.add(ListDv.get(i).getId());
												Bao_Cao_Tong_Hop.this.danhSachBCTH = Bao_Cao_Tong_Hop.this.resourceDv
														.toString().substring(1,
																Bao_Cao_Tong_Hop.this.resourceDv.toString().length()
																		- 1);
											}
										}
									}
								} else {
									if (Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId() == 2) {
										final List<DonViCanhsatGt> list = new DonViCanhsatGtDAO().getListDiaPhuong(
												Authentication.getAuthUser().getDonViCanhsatGt().getDonViResourceId());
										if (list.size() > 0) {
											for (int i = 0; i < list.size(); i++) {
												Bao_Cao_Tong_Hop.this.resourceDv.add(list.get(i).getId());
												Bao_Cao_Tong_Hop.this.danhSachBCTH = Bao_Cao_Tong_Hop.this.resourceDv
														.toString().substring(1,
																Bao_Cao_Tong_Hop.this.resourceDv.toString().length()
																		- 1);
											}
										}
									} else if (Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi()
											.getId() == 3) {
										final DonViCanhsatGtDAO DvDAO = new DonViCanhsatGtDAO();
										final List<DonViCanhsatGt> listDvPhong = DvDAO.getDVResourceBYCAP12(
												Authentication.getAuthUser().getDonViCanhsatGt().getId(),
												Authentication.getAuthUser().getDonViCanhsatGt().getId(),
												Authentication.getAuthUser().getDonViCanhsatGt().getDonViResourceId());
										if (listDvPhong.size() > 0) {
											for (int i = 0; i < listDvPhong.size(); i++) {
												Bao_Cao_Tong_Hop.this.resourceDv.add(listDvPhong.get(i).getId());
												Bao_Cao_Tong_Hop.this.danhSachBCTH = Bao_Cao_Tong_Hop.this.resourceDv
														.toString().substring(1,
																Bao_Cao_Tong_Hop.this.resourceDv.toString().length()
																		- 1);
											}
										}
									} else if (Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi()
											.getId() == 4) {
										List<DonViCanhsatGt> ListDv;
										final DonViCanhsatGtDAO DvDAO = new DonViCanhsatGtDAO();

										ListDv = DvDAO.DanhSachTheoDVi_taoMoi(
												Authentication.getAuthUser().getDonViCanhsatGt().getId());

										if (ListDv.size() > 0) {
											for (int i = 0; i < ListDv.size(); i++) {
												Bao_Cao_Tong_Hop.this.resourceDv.add(ListDv.get(i).getId());
												Bao_Cao_Tong_Hop.this.danhSachBCTH = Bao_Cao_Tong_Hop.this.resourceDv
														.toString().substring(1,
																Bao_Cao_Tong_Hop.this.resourceDv.toString().length()
																		- 1);
											}
										}
									}
								}
							}
						}

						private void setChildNotes(final Long resourceID) {
							final ArrayList<DonViCanhsatGt> resources = Bao_Cao_Tong_Hop.this.map.get(resourceID);
							if (resources != null && resources.size() != 0) {
								if (checkBox.getValue() == true) {
									for (int i = 0; i < resources.size(); i++) {
										Bao_Cao_Tong_Hop.this.resourceSet.add(resources.get(i).getId());
										setChildNotes(resources.get(i).getId());
									}
								} else {
									for (int i = 0; i < resources.size(); i++) {
										Bao_Cao_Tong_Hop.this.resourceSet.remove(resources.get(i).getId());
										setChildNotes(resources.get(i).getId());
									}
								}
							}
						}

					});

					return checkBox;
				}
				return null;
			}

		});
	}

	private void populateTreeTable() {
		long dvResource = 0;
		dvResource = Authentication.getAuthUser().getDonViCanhsatGt().getDonViResourceId();
		if (dvResource == 1) {
			if (Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId() == 1) {
				final DonViCanhsatGtDAO authResourceDAO = new DonViCanhsatGtDAO();
				final List<DonViCanhsatGt> list = authResourceDAO.getDVResourceKhacNull();
				final List<DonViCanhsatGt> list2 = authResourceDAO.getDVByCapDV4();
				final List<DonViCanhsatGt> list3 = authResourceDAO.getDVByCapDV3();

				this.treeTable.addContainerProperty("Đơn vị", String.class, "");
				// treeTableResources.setWidth("20em");
				for (final DonViCanhsatGt resource : list) {
					this.treeTable.addItem(new Object[] { resource.getTenDaydu() }, resource.getId());
					// this.treeTable.removeItem(resource.getId());
				}
				for (final DonViCanhsatGt resource : list3) {
					if (resource.getDonViResourceId() != null && resource.getDonViResourceId() != 0) {
						this.treeTable.setParent(resource.getId(), resource.getDonViResourceId());
					}
				}

				for (final DonViCanhsatGt resource : list2) {
					if (resource.getDvCsgtCaptrenId() != null && resource.getDvCsgtCaptrenId() != 0) {
						this.treeTable.setParent(resource.getId(), resource.getDvCsgtCaptrenId());
						this.treeTable.setChildrenAllowed(resource.getId(), false);
					}
				}
			}
			if (Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId() == 3) {
				final DonViCanhsatGtDAO DvDAO = new DonViCanhsatGtDAO();
				final List<DonViCanhsatGt> listDvPhong = DvDAO.getDVResourceBYCAP12(
						Authentication.getAuthUser().getDonViCanhsatGt().getId(),
						Authentication.getAuthUser().getDonViCanhsatGt().getId(),
						Authentication.getAuthUser().getDonViCanhsatGt().getDonViResourceId());
				final List<DonViCanhsatGt> listDvDoi = DvDAO.getDVByCapDV4();
				this.treeTable.addContainerProperty("Đơn vị", String.class, "");
				// treeTableResources.setWidth("20em");
				for (final DonViCanhsatGt resource : listDvPhong) {
					this.treeTable.addItem(new Object[] { resource.getTenDaydu() }, resource.getId());
				}
				for (final DonViCanhsatGt resource : listDvDoi) {
					if (resource.getDvCsgtCaptrenId() != null && resource.getDvCsgtCaptrenId() != 0) {
						this.treeTable.setParent(resource.getId(), resource.getDvCsgtCaptrenId());
						this.treeTable.setChildrenAllowed(resource.getId(), false);
					}
				}
			}
			if (Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId() == 4) {
				List<DonViCanhsatGt> ListDv;
				final DonViCanhsatGtDAO DvDAO = new DonViCanhsatGtDAO();
				ListDv = DvDAO.getDV(Authentication.getAuthUser().getDonViCanhsatGt().getId());
				this.treeTable.addContainerProperty("Đơn vị", String.class, "");
				for (final DonViCanhsatGt resource : ListDv) {
					this.treeTable.addItem(new Object[] { resource.getTenDaydu() }, resource.getId());
					this.treeTable.setChildrenAllowed(resource.getId(), false);
				}

			}
		} else {
			// this.cmbphong.setContainerDataSource(AuthGroup.class,
			// DAOs.get(AuthGroupDAO.class).getListRoleDiaPhuong());
			long DvResource = 0;
			DvResource = Authentication.getAuthUser().getDonViCanhsatGt().getDonViResourceId();
			if (DvResource != 0) {
				if (Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId() == 2) {
					final DonViCanhsatGtDAO authResourceDAO = new DonViCanhsatGtDAO();
					final List<DonViCanhsatGt> list = authResourceDAO.getListDiaPhuong(DvResource);
					final List<DonViCanhsatGt> list2 = authResourceDAO.getDVByCapDV4();
					final List<DonViCanhsatGt> list3 = authResourceDAO.getDVByCapDV3();

					this.treeTable.addContainerProperty("Đơn vị", String.class, "");
					// treeTableResources.setWidth("20em");
					for (final DonViCanhsatGt resource : list) {
						this.treeTable.addItem(new Object[] { resource.getTenDaydu() }, resource.getId());
					}
					for (final DonViCanhsatGt resource : list3) {
						if (resource.getDonViResourceId() != null && resource.getDonViResourceId() != 0) {
							this.treeTable.setParent(resource.getId(), resource.getDonViResourceId());
						}
					}

					for (final DonViCanhsatGt resource : list2) {
						if (resource.getDvCsgtCaptrenId() != null && resource.getDvCsgtCaptrenId() != 0) {
							this.treeTable.setParent(resource.getId(), resource.getDvCsgtCaptrenId());
							this.treeTable.setChildrenAllowed(resource.getId(), false);
						}
					}
				}
				if (Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId() == 3) {
					final DonViCanhsatGtDAO DvDAO = new DonViCanhsatGtDAO();
					final List<DonViCanhsatGt> listDvPhong = DvDAO.getDVResourceBYCAP12(
							Authentication.getAuthUser().getDonViCanhsatGt().getId(),
							Authentication.getAuthUser().getDonViCanhsatGt().getId(),
							Authentication.getAuthUser().getDonViCanhsatGt().getDonViResourceId());
					final List<DonViCanhsatGt> listDvDoi = DvDAO.getDVByCapDV4();
					this.treeTable.addContainerProperty("Đơn vị", String.class, "");
					// treeTableResources.setWidth("20em");
					for (final DonViCanhsatGt resource : listDvPhong) {
						this.treeTable.addItem(new Object[] { resource.getTenDaydu() }, resource.getId());
					}
					for (final DonViCanhsatGt resource : listDvDoi) {
						if (resource.getDvCsgtCaptrenId() != null && resource.getDvCsgtCaptrenId() != 0) {
							this.treeTable.setParent(resource.getId(), resource.getDvCsgtCaptrenId());
							this.treeTable.setChildrenAllowed(resource.getId(), false);
						}
					}
				}
				if (Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId() == 4) {
					this.treeTable.addContainerProperty("Đơn vị", String.class, "");
					List<DonViCanhsatGt> ListDv;
					final DonViCanhsatGtDAO DvDAO = new DonViCanhsatGtDAO();
					// final List<DanhSachDonViDacBiet> danhSach = new
					// DanhSachDonViDacBietDAO().TimChucNangDacBietTheoId(
					// Authentication.getAuthUser().getDonViCanhsatGt().getId(),
					// TAO_BIEN_BAN_CHO_DON_VI_CUNG_CAP);
					// if (danhSach.size() > 0) {
					// if (danhSach.get(0).getListDsDv() != null) {
					// ListDv =
					// DvDAO.NewDSDVDB(Authentication.getAuthUser().getDonViCanhsatGt().getId(),
					// danhSach.get(0).getListDsDv().toString(), 0);
					// } else {
					// ListDv = DvDAO.DanhSachTheoDViDbiet_taoMoi(
					// Authentication.getAuthUser().getDonViCanhsatGt().getId());
					// }
					// } else {
					ListDv = DvDAO.getDV(Authentication.getAuthUser().getDonViCanhsatGt().getId());
					// }
					for (final DonViCanhsatGt resource : ListDv) {
						this.treeTable.addItem(new Object[] { resource.getTenDaydu() }, resource.getId());
						this.treeTable.setChildrenAllowed(resource.getId(), false);
					}
				}
			}
		}
	}

	public static String spilit(final String spilit, final char a) {
		String result = "";
		for (int i = 0; i < spilit.length(); i++) {
			if (spilit.charAt(i) != a) {
				result += spilit.charAt(i);
			} else {
				break;
			}
		}
		return result;

	}

	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		final String filePath = ("D:/");
		final String fileName = ("BaoCaoTongHop.xls");
		File newFile = new File(filePath);
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=BaoCaoTongHop.xls");
		try {
			newFile = new File(filePath, fileName);
			final FileInputStream in = new FileInputStream(newFile);
			final ServletOutputStream out = response.getOutputStream();
			IOUtil.copyCompletely(in, out);
			in.close();
			out.flush();
			out.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public String substr(final String result) {

		final String[] parts = result.split("nơi cấp");
		final int count = result.split("nơi cấp").length;
		String b = "";
		final int j = 0;
		final String cars[] = new String[count];
		for (int i = 0; i < count - 1 || i == count - 1; i++) {

			if (parts[i].contains(";")) {
				final int dem = parts[i].indexOf(";");

				{
					if (count > 2) {
						b = parts[i].substring(1, dem) + "; " + b;
					}
					if (count == 2) {
						b = parts[i].substring(1, dem);
					}
					// cars[j]=b;
					// System.out.println(cars[j]);
					// j++;
					//
				}
			}
		}
		return b;
	}

	public String substrHvvp(final String result) {
		final String[] parts = result.split("quy định tại");
		final int count = result.split("quy định tại").length;
		String b = "";
		final int j = 0;
		final String cars[] = new String[count];
		for (int i = 0; i < count - 1 || i == count - 1; i++) {

			if (parts[i].contains(";")) {
				final int dem = parts[i].indexOf(";");

				{
					b = parts[i].substring(1, dem) + "; " + b;
				}

			}
			if (parts[i].contains(".")) {
				if (parts[i].contains(" của")) {
					final int dem1 = parts[i].indexOf(" của");

					{
						b = parts[i].substring(1, dem1) + "; " + b;
					}
				} else {
					b = parts[i];
				}
			}
		}
		return b;
	}

	public String SubStrLuat(final String result) {
		final String[] parts = result.split("quy định tại");
		final int count = result.split("quy định tại").length;
		String b = "";
		final int j = 0;
		final String cars[] = new String[count];
		for (int i = 0; i < count - 1 || i == count - 1; i++) {

			if (parts[i].contains(";") && !parts[i].contains("quy định XPHC")) {
				final int dem = parts[i].indexOf(";");

				{
					b += parts[i].substring(1, dem) + "; ";
				}

			}
			if (parts[i].contains("quy định XPHC")) {
				final int dem1 = parts[i].indexOf("quy định XPHC");

				{
					b += parts[i].substring(1, dem1).trim() + "; ";
				}
			}
			// else {
			// b = parts[i];
			// }

		}
		return b;
	}

	public String substrFromNghiD(final String result) {
		final String[] parts = result.split("quy định tại");
		final int count = result.split("quy định tại").length;
		String b = "";
		final int j = 0;
		final String cars[] = new String[count];
		for (int i = 0; i < count - 2 || i == count - 2; i++) {
			if (parts[i].contains(";")) {
				b = parts[i].split(";", 2)[1].trim() + "; " + b;
			} else {
				b = parts[i].trim() + "; " + b;
			}
		}
		return b;
	}

	public static String typefile(final String path) {

		String result = "";
		for (int i = path.length() - 1; i >= 0; i--) {
			if (path.charAt(i) != ':') {
				result = path.charAt(i) + result;
			} else {
				break;
			}
		}
		return result;
	}

	public static String StringYear(final String path) {

		String result = "";
		for (int i = path.length() - 1; i >= 0; i--) {
			if (path.charAt(i) != '/') {
				result = path.charAt(i) + result;
			} else {
				break;
			}
		}
		return result;
	}

	public static String NoiDungHvvp(final String result) {
		String b = "";
		final int count = result.split("quy định tại").length;
		for (int i = 0; i < count; i++) {
			if (result.contains("quy định tại")) {
				b = result.substring(i) + b;
			} else {
				break;
			}
		}
		return result;
	}

	/**
	 * Event handler delegate method for the {@link XdevButton}
	 * {@link #btnbaocaoTongHop}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnbaocaoTongHop_buttonClick(final Button.ClickEvent event) {
		long toChuc = -1;
		if ((this.cmbToChuc.getValue() != "" && this.cmbToChuc.getValue() != null)
				|| this.cmbToChuc.getValue() != "Tất cả") {
			if (this.cmbToChuc.getValue() == "Cá nhân") {
				toChuc = 0;
			} else if (this.cmbToChuc.getValue() == "Tổ chức") {
				toChuc = 1;
			}
		}
		long linhVuc = 0;
		if ((this.cmbLinhVuc.getValue() != "" && this.cmbLinhVuc.getValue() != null)
				|| this.cmbLinhVuc.getValue() != "Tất cả") {
			if (this.cmbLinhVuc.getValue() == "Đường bộ") {
				linhVuc = 1;
			} else if (this.cmbLinhVuc.getValue() == "Đường sắt") {
				linhVuc = 2;
			} else if (this.cmbLinhVuc.getValue() == "Đường thủy") {
				linhVuc = 4;
			}
		}
		String ngheNghiep = "";
		if (this.cmbNgheNghiep.getSelectedItem() != null) {
			ngheNghiep = this.cmbNgheNghiep.getSelectedItem().getBean().getTenNgheNghiep();
		}
		String FromYearOfBirth = "";
		if (this.txtNamSinhTu.isEmpty() == false) {
			FromYearOfBirth = this.txtNamSinhTu.getValue().trim();
		}
		String ToYearOfBirth = "";
		if (this.txtNamSinhDen.isEmpty() == false) {
			ToYearOfBirth = this.txtNamSinhDen.getValue().trim();
		}
		String lpt = "";
		if (this.cmbLoaiPt.getSelectedItem() != null) {
			lpt = this.cmbLoaiPt.getSelectedItem().getBean().getLoaiPt();
		}
		String loaiGiayTo = "";
		if ((this.cmbLoaiGiayTo.getValue() != "" && this.cmbLoaiGiayTo.getValue() != null)
				&& this.cmbLoaiGiayTo.getValue() != "Tất cả") {
			loaiGiayTo = this.cmbLoaiGiayTo.getValue().toString().trim();
		}
		String hinhThucXp = "";
		if (this.cmbHinhThucPhat.getSelectedItem() != null) {
			hinhThucXp = this.cmbHinhThucPhat.getSelectedItem().getBean().getTenHinhThuc();
		}
		String fromMoney = "";
		if (this.txtPhatTienTu.isEmpty() == false) {
			fromMoney = this.txtPhatTienTu.getValue().trim().replace(".", "");
		}
		String toMoney = "";
		if (this.txtPhatTienDen.isEmpty() == false) {
			toMoney = this.txtPhatTienDen.getValue().trim().replace(".", "");
			;
		}
		String hinhThucNp = "";
		if (this.cmbHinhThucNP.isEmpty() == false || this.cmbHinhThucNP.getValue() != "Tất cả") {
			if (this.cmbHinhThucNP.getValue() == "Qua DVC") {
				hinhThucNp = "3, 4";
			}
			if (this.cmbHinhThucNP.getValue() == "Trực tiếp") {
				hinhThucNp = "5";
			}
		}
		final SimpleDateFormat dfm2 = new SimpleDateFormat("dd/MM/yyyy");
		String tuocFrom = "";
		if (this.pdFTuocTuNgay.isEmpty() == false) {
			tuocFrom = dfm2.format(this.pdFTuocTuNgay.getValue());
		}
		String tuocTo = "";
		if (this.pdFTuocDenNgay.isEmpty() == false) {
			tuocTo = dfm2.format(this.pdFTuocDenNgay.getValue());
		}
		String xpbs = "";
		if (this.cmbXpbs.isEmpty() == false) {
			xpbs = this.cmbXpbs.getSelectedItem().getBean().getGhiChu();
		}
		long idHvvp = 0;
		if (this.cmbHvvp.getSelectedItem() != null) {
			idHvvp = this.cmbHvvp.getSelectedItem().getBean().getLuatId();
		}
		String chucVu = "";
		if (this.cmbCapPheDuyet.getSelectedItem() != null) {
			chucVu = this.cmbCapPheDuyet.getSelectedItem().getBean().getTenChucVu().toUpperCase().trim();
		}
		String khoBac = "";
		if (this.cmbNopTrucTuyenQua.getSelectedItem() != null) {
			khoBac = this.cmbNopTrucTuyenQua.getSelectedItem().getBean().getTen();
		}
		String tenNhom = "";
		if (this.cmbNhomHv.getValue() != null && this.cmbNhomHv.getValue() != "") {
			if (this.cmbNhomHv.getValue().toString().contains("Tất cả")) {
				tenNhom = "";
			} else {
				tenNhom = this.cmbNhomHv.getValue().toString();
			}
		}
		String nghiDinh = "";
		if (this.cmbNghiDinh.getSelectedItem() != null) {
			nghiDinh = this.cmbNghiDinh.getSelectedItem().getBean().getMa();
		}
		if (this.FromDate.isEmpty() == false) {
			final HttpServletRequest request = null;
			final HttpServletResponse response = null;
			final Date fromdate = this.FromDate.getValue();
			final Date todate = this.ToDate.getValue();
			System.out.println("Create file excel");
			final XSSFWorkbook workbook = new XSSFWorkbook();
			final XSSFSheet sheet = workbook.createSheet("Báo Cáo Tổng Hợp");

			final XSSFFont font = workbook.createFont();
			final XSSFFont font1 = workbook.createFont();
			final XSSFFont fontRed = workbook.createFont();
			fontRed.setColor(HSSFColor.RED.index);
			fontRed.setBold(true);
			sheet.setColumnWidth(1, 3500);
			sheet.setColumnWidth(2, 8000);
			sheet.setColumnWidth(3, 8000);
			sheet.setColumnWidth(4, 5000);
			sheet.setColumnWidth(5, 10000);
			sheet.setColumnWidth(6, 5000);
			sheet.setColumnWidth(7, 15000);
			sheet.setColumnWidth(8, 5000);
			sheet.setColumnWidth(9, 5000);
			sheet.setColumnWidth(10, 5000);
			sheet.setColumnWidth(11, 5000);
			sheet.setColumnWidth(12, 5000);
			sheet.setColumnWidth(13, 5000);
			sheet.setColumnWidth(14, 5000);
			sheet.setColumnWidth(15, 25000);
			sheet.setColumnWidth(16, 7000);
			sheet.setColumnWidth(17, 5000);
			sheet.setColumnWidth(18, 25000);
			sheet.setColumnWidth(19, 5000);
			sheet.setColumnWidth(20, 5000);
			sheet.setColumnWidth(21, 5000);
			sheet.setColumnWidth(22, 6000);
			sheet.setColumnWidth(23, 6000);
			sheet.setColumnWidth(24, 5000);
			sheet.setColumnWidth(25, 5000);
			sheet.setColumnWidth(26, 5000);
			sheet.setColumnWidth(27, 5000);
			sheet.setColumnWidth(28, 6000);
			sheet.setColumnWidth(29, 6000);
			sheet.setColumnWidth(30, 5000);
			sheet.setColumnWidth(31, 5000);
			sheet.setColumnWidth(32, 5000);
			sheet.setColumnWidth(33, 5000);
			sheet.setColumnWidth(34, 5000);
			sheet.setColumnWidth(35, 5000);

			font.setBold(true);
			font.setFontName("Times New Roman");
			final XSSFCellStyle cellborder = workbook.createCellStyle();
			cellborder.setBorderBottom(BorderStyle.THIN);
			cellborder.setBorderTop(BorderStyle.THIN);
			cellborder.setBorderLeft(BorderStyle.THIN);
			cellborder.setBorderRight(BorderStyle.THIN);
			font1.setFontName("Times New Roman");
			cellborder.setFont(font1);
			cellborder.setAlignment(HorizontalAlignment.LEFT);
			cellborder.setWrapText(true);
			final XSSFCellStyle cellborderB = workbook.createCellStyle();
			// cellborderB.setBorderBottom(BorderStyle.THIN);
			// cellborderB.setBorderTop(BorderStyle.THIN);
			// cellborderB.setBorderLeft(BorderStyle.THIN);
			// cellborderB.setBorderRight(BorderStyle.THIN);
			cellborderB.setFont(font);
			cellborderB.setAlignment(HorizontalAlignment.CENTER);

			final XSSFCellStyle style = workbook.createCellStyle();
			final XSSFCellStyle style1 = workbook.createCellStyle();
			final XSSFCellStyle styleredbold = workbook.createCellStyle();
			final XSSFCellStyle stylecenter = workbook.createCellStyle();
			final XSSFCellStyle styleWrap = workbook.createCellStyle();
			final XSSFCellStyle styleUpper = workbook.createCellStyle();
			styleUpper.setFont(font1);
			final XSSFCellStyle styleWrapNoborder = workbook.createCellStyle();
			styleWrapNoborder.setAlignment(HorizontalAlignment.JUSTIFY);
			styleWrapNoborder.setWrapText(true);
			styleWrapNoborder.setFont(font);
			styleWrap.setWrapText(true);
			styleWrap.setBorderBottom(BorderStyle.THIN);
			styleWrap.setBorderTop(BorderStyle.THIN);
			styleWrap.setBorderLeft(BorderStyle.THIN);
			styleWrap.setBorderRight(BorderStyle.THIN);
			styleWrap.setFont(font1);
			stylecenter.setAlignment(HorizontalAlignment.CENTER);
			style1.setAlignment(HorizontalAlignment.CENTER);
			final XSSFFont fontBaocao = workbook.createFont();
			final Font fontBaocao1 = new Font();
			fontBaocao1.setSize(16);
			fontBaocao1.isBold();
			style1.setFont(font);
			style.setFont(font);
			styleredbold.setFont(font);
			int rowNum = 0;

			// tieu de
			final Row row = sheet.createRow(rowNum++);
			final Cell cell1 = row.createCell(0);
			final int cellrange = sheet.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));
			cell1.setCellValue("BÁO CÁO TỔNG HỢP");
			cell1.setCellStyle(style1);

			// tu ngay den ngay
			final Row rowNgay = sheet.createRow(rowNum++);
			final Cell cell2 = rowNgay.createCell(0);
			if (this.FromDate.isEmpty() != true && this.ToDate.getValue() != null) {
				final Date fromDate = this.FromDate.getValue();
				final Date dateStr = this.ToDate.getValue();
				final DateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");
				// final int cellrange2= sheet.addMergedRegion(new
				// CellRangeAddress(rowNum++,rowNum++,0,12));
				final int cellrange1 = sheet.addMergedRegionUnsafe(CellRangeAddress.valueOf("A2:F2"));
				cell2.setCellValue("Từ ngày " + dfm.format(fromDate) + " đến ngày " + dfm.format(dateStr));
				cell2.setCellStyle(style1);
				final DateFormat dfm1 = new SimpleDateFormat("dd-MMM-yy");
				this.fromdateBCTH = dfm1.format(fromDate);
				this.todateBCTH = dfm1.format(dateStr);

			}
			if (this.FromDate.isEmpty() != true && this.ToDate.getValue() == null) {
				final Date fromDate = this.FromDate.getValue();
				final Date dateStr = new Date();
				final DateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");
				// final int cellrange2= sheet.addMergedRegion(new
				// CellRangeAddress(rowNum++,rowNum++,0,12));
				final int cellrange1 = sheet.addMergedRegionUnsafe(CellRangeAddress.valueOf("A2:F2"));
				cell2.setCellValue("Từ ngày " + dfm.format(fromDate) + " đến ngày " + dfm.format(dateStr));
				cell2.setCellStyle(style1);
				final DateFormat dfm1 = new SimpleDateFormat("dd-MMM-yy");
				this.fromdateBCTH = dfm1.format(fromDate);
				this.todateBCTH = dfm1.format(dateStr);

			}
			// ten cot
			final Row row1 = sheet.createRow(rowNum++);
			final Cell A4 = row1.createCell(0);
			A4.setCellValue("STT");
			A4.setCellStyle(cellborderB);
			final Cell B4 = row1.createCell(1);
			B4.setCellValue("Mã vụ việc");
			B4.setCellStyle(cellborderB);
			final Cell C4 = row1.createCell(2);
			C4.setCellValue("Loại BB/QĐ");
			C4.setCellStyle(cellborderB);
			final Cell D4 = row1.createCell(3);
			D4.setCellValue("Số BB/QĐ");
			D4.setCellStyle(cellborderB);
			final Cell E4 = row1.createCell(4);
			E4.setCellValue("Đối tượng vi phạm");
			E4.setCellStyle(cellborderB);
			final Cell F4 = row1.createCell(5);
			F4.setCellValue("Tên");
			F4.setCellStyle(cellborderB);
			final Cell G4 = row1.createCell(6);
			G4.setCellValue("Thời gian vi phạm");
			G4.setCellStyle(cellborderB);
			final Cell H4 = row1.createCell(7);
			H4.setCellValue("Địa chỉ");
			H4.setCellStyle(cellborderB);
			final Cell I4 = row1.createCell(8);
			I4.setCellValue("Giới tính");
			I4.setCellStyle(cellborderB);
			final Cell J4 = row1.createCell(9);
			J4.setCellValue("Ngày sinh");
			J4.setCellStyle(cellborderB);
			final Cell K4 = row1.createCell(10);
			K4.setCellValue("Tuổi");
			K4.setCellStyle(cellborderB);
			final Cell L4 = row1.createCell(11);
			L4.setCellValue("Nghề nghiệp");
			L4.setCellStyle(cellborderB);
			final Cell M4 = row1.createCell(12);
			M4.setCellValue("Loại phương tiện");
			M4.setCellStyle(cellborderB);
			final Cell N4 = row1.createCell(13);
			N4.setCellValue("Biển số");
			N4.setCellStyle(cellborderB);
			final Cell O4 = row1.createCell(14);
			O4.setCellValue("Địa điểm vi phạm");
			O4.setCellStyle(cellborderB);
			final Cell P4 = row1.createCell(15);
			P4.setCellValue("Nội dung vi phạm");
			P4.setCellStyle(cellborderB);
			final Cell Q4 = row1.createCell(16);
			Q4.setCellValue("Điều, khoản, điểm, VP/ND");
			Q4.setCellStyle(cellborderB);
			final Cell R4 = row1.createCell(17);
			R4.setCellValue("Nhóm HVVP");
			R4.setCellStyle(cellborderB);
			final Cell S4 = row1.createCell(18);
			S4.setCellValue("Tạm giữ");
			S4.setCellStyle(cellborderB);
			final Cell T4 = row1.createCell(19);
			T4.setCellValue("Hạng giấy tờ");
			T4.setCellStyle(cellborderB);
			final Cell U4 = row1.createCell(20);
			U4.setCellValue("Số giấy tờ tạm giữ");
			U4.setCellStyle(cellborderB);
			final Cell V4 = row1.createCell(21);
			V4.setCellValue("Thời hạn giấy tờ");
			V4.setCellStyle(cellborderB);
			final Cell W4 = row1.createCell(22);
			W4.setCellValue("Nơi cấp giấy tờ");
			W4.setCellStyle(cellborderB);
			final Cell X4 = row1.createCell(23);
			X4.setCellValue("Thời hạn tạm giữ phương tiện");
			X4.setCellStyle(cellborderB);
			final Cell Y4 = row1.createCell(24);
			Y4.setCellValue("Hình thức xử phạt");
			Y4.setCellStyle(cellborderB);

			final Cell Z4 = row1.createCell(25);
			Z4.setCellValue("Số tiền");
			Z4.setCellStyle(cellborderB);
			final Cell AA4 = row1.createCell(26);
			AA4.setCellValue("Hình thức nộp phạt");
			AA4.setCellStyle(cellborderB);
			final Cell AB4 = row1.createCell(27);
			AB4.setCellValue("Hình phạt bổ sung");
			AB4.setCellStyle(cellborderB);
			final Cell AC4 = row1.createCell(28);
			AC4.setCellValue("Thời gian bắt đầu HPBS");
			AC4.setCellStyle(cellborderB);
			final Cell AD4 = row1.createCell(29);
			AD4.setCellValue("Thời gian kết thức HPBS");
			AD4.setCellStyle(cellborderB);
			final Cell AE4 = row1.createCell(30);
			AE4.setCellValue("Biện pháp khắc phục hậu quả");
			AE4.setCellStyle(cellborderB);
			final Cell AT4 = row1.createCell(31);
			AT4.setCellValue("Tang vật trả lại");
			AT4.setCellStyle(cellborderB);
			final Cell AF4 = row1.createCell(32);
			AF4.setCellValue("Ngày lập");
			AF4.setCellStyle(cellborderB);
			final Cell AM4 = row1.createCell(33);
			AM4.setCellValue("Đơn vị lập");
			AM4.setCellStyle(cellborderB);
			final Cell AG4 = row1.createCell(34);
			AG4.setCellValue("Đơn vị xử lý");
			AG4.setCellStyle(cellborderB);
			final Cell AH4 = row1.createCell(35);
			AH4.setCellValue("Cán bộ xử lý");
			AH4.setCellStyle(cellborderB);
			final Cell AI4 = row1.createCell(36);
			AI4.setCellValue("Cấp phê duyệt");
			AI4.setCellStyle(cellborderB);
			final Cell AJ4 = row1.createCell(37);
			AJ4.setCellValue("Lãnh đạo phê duyệt");
			AJ4.setCellStyle(cellborderB);
			final Cell AK4 = row1.createCell(38);
			AK4.setCellValue("Địa bàn vi phạm");
			AK4.setCellStyle(cellborderB);
			final Cell AL4 = row1.createCell(39);
			AL4.setCellValue("Lĩnh vực");
			AL4.setCellStyle(cellborderB);

			final Table table = new Table();
			int count1 = 1;
			table.addContainerProperty("STT", String.class, null);
			table.addContainerProperty("MA_VU_VIEC", String.class, null);
			table.addContainerProperty("MA_RUTGON", String.class, null);
			table.addContainerProperty("LOAI_BB_QD", String.class, null);
			table.addContainerProperty("SO_BIEN_BAN", String.class, null);
			table.addContainerProperty("TEN_NGUOI_NVP", String.class, null);
			table.addContainerProperty("DIA_DANH_HC_ID", String.class, null);
			table.addContainerProperty("DIA_CHI_NVP", String.class, null);
			table.addContainerProperty("NGAY_SINH_NVP_NHAP", String.class, null);
			table.addContainerProperty("NGHE_NGHIEP_NVP", String.class, null);
			table.addContainerProperty("LOAI_PHUONG_TIEN", String.class, null);
			table.addContainerProperty("NOI_DUNG_VPHC", String.class, null);
			table.addContainerProperty("BIEN_KIEM_SOAT", String.class, null);
			table.addContainerProperty("HANG_GPLX", String.class, null);
			table.addContainerProperty("GPLX", String.class, null);
			table.addContainerProperty("THOI_GIAN_VPHC", String.class, null);
			table.addContainerProperty("DIA_DIEM_VPHC", String.class, null);
			table.addContainerProperty("TANG_VAT_TG", String.class, null);
			table.addContainerProperty("THOI_HAN_TG", String.class, null);
			table.addContainerProperty("HINH_THUC_XP", String.class, null);
			table.addContainerProperty("TONG_MUC_PHAT", String.class, null);
			table.addContainerProperty("XU_PHAT_BO_SUNG", String.class, null);
			table.addContainerProperty("TU_NGAY_XPBS", String.class, null);
			table.addContainerProperty("DEN_NGAY_XPBS", String.class, null);
			table.addContainerProperty("BIEN_PHAP_KHAC_PHUC", String.class, null);
			table.addContainerProperty("NGAY_LAP_BB", Date.class, null);
			table.addContainerProperty("TEN_DON_VI", String.class, null);
			table.addContainerProperty("TEN_CAN_BO", String.class, null);
			table.addContainerProperty("LINH_VUC_GIAO_THONG", String.class, null);
			table.addContainerProperty("TO_CHUC", String.class, null);
			table.addContainerProperty("KIEM_DINH", String.class, null);
			table.addContainerProperty("TRANG_THAI_NP", String.class, null);
			table.addContainerProperty("HANH_VI_VP_ID", String.class, null);
			table.addContainerProperty("LUAT_TC_ID", String.class, null);
			table.addContainerProperty("TUOC_TU_NGAY", String.class, null);
			table.addContainerProperty("TUOC_DEN_NGAY", String.class, null);
			table.addContainerProperty("CAP_BAC_CHUC_VU", String.class, null);
			table.addContainerProperty("DON_VI_THU_TIEN", String.class, null);
			table.addContainerProperty("TANG_VAT_TRA_LAI", String.class, null);
			table.addContainerProperty("TEN_DON_VI_LAP", String.class, null);
			table.addContainerProperty("LOAI_TVTG_ID", String.class, null);
			table.addContainerProperty("LOAI_TVTG", String.class, null);
			table.addContainerProperty("HANG_TVTG", String.class, null);
			table.addContainerProperty("NOI_CAP_TVTG", String.class, null);
			table.addContainerProperty("HIEU_LUC_TVTG", String.class, null);
			table.addContainerProperty("TINH_TRANG", String.class, null);
			table.addContainerProperty("SO_LUONG", String.class, null);
			table.addContainerProperty("DON_VI_TINH", String.class, null);
			table.addContainerProperty("SO_TVTG", String.class, null);
			table.addContainerProperty("NOI_DUNG_HVVP", String.class, null);
			table.addContainerProperty("DIEU_LUAT", String.class, null);
			table.addContainerProperty("THOI_HAN_TU", String.class, null);
			table.addContainerProperty("THOI_HAN_DEN", String.class, null);
			table.addContainerProperty("TANG_VAT", String.class, null);

			if ((this.cmbToChuc.getValue() == "Tất cả"
					|| (this.cmbToChuc.getValue() == "" && this.cmbToChuc.getValue() == null))
					&& (this.cmbLinhVuc.getValue() == "Tất cả"
							|| (this.cmbLinhVuc.getValue() == "" && this.cmbLinhVuc.getValue() == null))
					&& this.cmbNgheNghiep.getSelectedItem() == null && this.txtNamSinhDen.isEmpty()
					&& this.txtNamSinhTu.isEmpty()
					&& (this.cmbLoaiGiayTo.getValue() == "Tất cả"
							|| (this.cmbLoaiGiayTo.getValue() == "" && this.cmbLoaiGiayTo.getValue() == null))
					&& this.cmbCapPheDuyet.getSelectedItem() == null && this.cmbLoaiPt.getSelectedItem() == null
					&& this.cmbHinhThucPhat.getSelectedItem() == null && this.txtPhatTienDen.isEmpty()
					&& (this.cmbHinhThucNP.getValue() == "Tất cả"
							|| (this.cmbHinhThucNP.getValue() == "" && this.cmbHinhThucNP.getValue() == null))
					&& this.cmbNopTrucTuyenQua.getSelectedItem() == null && this.cmbXpbs.getSelectedItem() == null
					&& this.pdFTuocDenNgay.isEmpty() && this.pdFTuocTuNgay.isEmpty()
					&& this.cmbHvvp.getSelectedItem() == null
					&& ((this.cmbNhomHv.getValue() == "Tất cả")
							|| (this.cmbNhomHv.getValue() == "" && this.cmbNhomHv.getValue() == null))
					&& this.cmbNghiDinh.getSelectedItem() == null && this.txtTenNvp.isEmpty()
					&& this.txtDiaChiNvp.isEmpty() && this.cmbNoiCapTv.getSelectedItem() == null
					&& this.txtSoGiayTo.isEmpty() && this.cmbHangGplx.getSelectedItem() == null && this.txtBKS.isEmpty()
					&& this.txtSoBb.isEmpty() && this.cmbCanBoLap.getSelectedItem() == null
					&& (this.cmbLoaiBb.getValue() == "Tất cả"
							|| (this.cmbLoaiBb.getValue() == "" && this.cmbLoaiBb.getValue() == null))
					&& this.txtTuoiTu.isEmpty() && this.txtTuoiDen.isEmpty() && this.TrangThaiXuLyBCTH == -1
					&& this.thoiHanTuBCTH == "" && this.thoiHanDenBCTH == "" && this.TinhBCTH == ""
					&& this.QuanBCTH == "" && this.XaBCTH == "" && this.QuocLoBCTH == "" && this.TuyenDuongBCTH == "") {

				final ViewBaocaothQd01DAO dao01 = new ViewBaocaothQd01DAO();
				final List<ViewBaocaothQd01> listVuViec01;
				if (this.danhSachBCTH != "") {
					listVuViec01 = dao01.BaoCaoTongHop(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH);
				} else {
					listVuViec01 = dao01.BaoCaoTongHop("", this.fromdateBCTH, this.todateBCTH);
				}
				for (final ViewBaocaothQd01 vuviec01 : listVuViec01) {
					count1++;
					table.addItem(new Object[] { String.valueOf(listVuViec01.indexOf(vuviec01) + 1),
							String.valueOf(vuviec01.getMaVuViec()), String.valueOf(vuviec01.getMaRutgon()),
							String.valueOf(vuviec01.getLoaiBbQd()), String.valueOf(vuviec01.getSoBienBan()),
							String.valueOf(vuviec01.getTenNguoiNvp()), String.valueOf(vuviec01.getDiaDanhHcId()),
							vuviec01.getDiaChiNvp(), vuviec01.getNgaySinhNvpNhap(), vuviec01.getNgheNghiepNvp(),
							vuviec01.getLoaiPhuongTien(), vuviec01.getNoiDungVphc(), vuviec01.getBienKiemSoat(),
							vuviec01.getHangGplx(), vuviec01.getGplx(), vuviec01.getThoiGianVphc(),
							vuviec01.getDiaDiemVphc(), vuviec01.getTangVatTg(), vuviec01.getThoiHanTg(),
							vuviec01.getHinhThucXp(),
							vuviec01.getTongMucPhat() == null ? null : (String.valueOf(vuviec01.getTongMucPhat())),
							vuviec01.getXuPhatBoSung(),
							vuviec01.getTuNgayXpbs() == null ? "" : (String.valueOf(vuviec01.getTuNgayXpbs())),
							vuviec01.getDenNgayXpbs() == null ? "" : (String.valueOf(vuviec01.getDenNgayXpbs())),
							vuviec01.getBienPhapKhacPhuc(), vuviec01.getNgayLapBb(), vuviec01.getTenDonVi(),
							vuviec01.getTenCanBo(),
							vuviec01.getLinhVucGiaoThong() == null ? ""
									: String.valueOf(vuviec01.getLinhVucGiaoThong()),
							String.valueOf(vuviec01.getToChuc()), vuviec01.getKiemDinh(),
							vuviec01.getTrangThaiNp() == null ? "" : String.valueOf(vuviec01.getTrangThaiNp()),
							vuviec01.getHanhViVpId() == null ? "" : String.valueOf(vuviec01.getHanhViVpId()),
							vuviec01.getLuatTcId() == null ? "" : String.valueOf(vuviec01.getLuatTcId()),
							vuviec01.getTuocTuNgay() == null ? "" : (String.valueOf(vuviec01.getTuocTuNgay())),
							vuviec01.getTuocDenNgay() == null ? "" : (String.valueOf(vuviec01.getTuocDenNgay())),
							vuviec01.getCapBacChucVu(), vuviec01.getDonViThuTien(), vuviec01.getTangVatTraLai(),
							vuviec01.getTenDonViLap(), vuviec01.getLoaiTvtgId(), vuviec01.getLoaiTvtg(),
							vuviec01.getHangTvtg(), vuviec01.getNoiCapTvtg(), vuviec01.getHieuLucTvtg(),
							vuviec01.getTinhTrang(), vuviec01.getSoLuong(), vuviec01.getDonViTinh(),
							vuviec01.getSoTvtg(), vuviec01.getNoiDungHvvp(), vuviec01.getDieuLuat(),
							vuviec01.getThoiHanTu(), vuviec01.getThoiHanDen(), vuviec01.getTangVat() }, count1);

				}
				final ViewBaocaothBb43DAO dao43 = new ViewBaocaothBb43DAO();
				final List<ViewBaocaothBb43> listVuViec43;
				if (this.danhSachBCTH != "") {
					listVuViec43 = dao43.BaoCaoTongHop(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH);
				} else {
					listVuViec43 = dao43.BaoCaoTongHop("", this.fromdateBCTH, this.todateBCTH);
				}
				for (final ViewBaocaothBb43 vuviec43 : listVuViec43) {
					count1++;
					table.addItem(new Object[] { String.valueOf(listVuViec43.indexOf(vuviec43) + 1),
							vuviec43.getMaVuViec(), vuviec43.getMaRutgon(), String.valueOf(vuviec43.getLoaiBbQd()),
							vuviec43.getSoBienBan(), vuviec43.getTenNguoiNvp(),
							String.valueOf(vuviec43.getDiaDanhHcId()), vuviec43.getDiaChiNvp(),
							vuviec43.getNgaySinhNvpNhap(), vuviec43.getNgheNghiepNvp(), vuviec43.getLoaiPhuongTien(),
							vuviec43.getNoiDungVphc(), vuviec43.getBienKiemSoat(), vuviec43.getHangGplx(),
							vuviec43.getGplx(), vuviec43.getThoiGianVphc(), vuviec43.getDiaDiemVphc(),
							vuviec43.getTangVatTg(), vuviec43.getThoiHanTg(), vuviec43.getHinhThucXp(),
							vuviec43.getTongMucPhat() == null ? "" : String.valueOf(vuviec43.getTongMucPhat()),
							vuviec43.getXuPhatBoSung(), vuviec43.getTuNgayXpbs(), vuviec43.getDenNgayXpbs(),
							vuviec43.getBienPhapKhacPhuc(), vuviec43.getNgayLapBb(), vuviec43.getTenDonVi(),
							vuviec43.getTenCanBo(),
							vuviec43.getLinhVucGiaoThong() == null ? ""
									: String.valueOf(vuviec43.getLinhVucGiaoThong()),
							String.valueOf(vuviec43.getToChuc()), vuviec43.getKiemDinh(), vuviec43.getTrangThaiNp(),
							String.valueOf(vuviec43.getHanhViVpId()), String.valueOf(vuviec43.getLuatTcId()),
							vuviec43.getTuocTuNgay() == null ? "" : (String.valueOf(vuviec43.getTuocTuNgay())),
							vuviec43.getTuocDenNgay() == null ? "" : (String.valueOf(vuviec43.getTuocDenNgay())),
							vuviec43.getCapBacChucVu(), vuviec43.getDonViThuTien(), vuviec43.getTangVatTraLai(),
							vuviec43.getTenDonViLap(), String.valueOf(vuviec43.getLoaiTvtgId()), vuviec43.getLoaiTvtg(),
							vuviec43.getHangTvtg(), vuviec43.getNoiCapTvtg(), vuviec43.getHieuLucTvtg(),
							vuviec43.getTinhTrang(), String.valueOf(vuviec43.getSoLuong()), vuviec43.getDonViTinh(),
							vuviec43.getSoTvtg(), vuviec43.getNoiDungHvvp(), vuviec43.getDieuLuat(),
							vuviec43.getThoiHanTu(), vuviec43.getThoiHanDen(), vuviec43.getTangVat() }, count1);

				}
				final ViewBaocaothBb50DAO dao50 = new ViewBaocaothBb50DAO();
				final List<ViewBaocaothBb50> listVuViec50;
				listVuViec50 = dao50.BaoCaoTongHop(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH);
				for (final ViewBaocaothBb50 vuviec50 : listVuViec50) {
					count1++;
					table.addItem(new Object[] { String.valueOf(listVuViec50.indexOf(vuviec50) + 1),
							vuviec50.getMaVuViec(), vuviec50.getMaRutgon(), String.valueOf(vuviec50.getLoaiBbQd()),
							vuviec50.getSoBienBan(), vuviec50.getTenNguoiNvp(),
							String.valueOf(vuviec50.getDiaDanhHcId()), vuviec50.getDiaChiNvp(),
							vuviec50.getNgaySinhNvpNhap(), vuviec50.getNgheNghiepNvp(), vuviec50.getLoaiPhuongTien(),
							vuviec50.getNoiDungVphc(), vuviec50.getBienKiemSoat(), vuviec50.getHangGplx(),
							vuviec50.getGplx(), vuviec50.getThoiGianVphc(), vuviec50.getDiaDiemVphc(),
							vuviec50.getTangVatTg(), vuviec50.getThoiHanTg(), vuviec50.getHinhThucXp(),
							vuviec50.getTongMucPhat(), vuviec50.getXuPhatBoSung(), vuviec50.getTuNgayXpbs(),
							vuviec50.getDenNgayXpbs(), vuviec50.getBienPhapKhacPhuc(), vuviec50.getNgayLapBb(),
							vuviec50.getTenDonVi(), vuviec50.getTenCanBo(),
							vuviec50.getLinhVucGiaoThong() == null ? ""
									: String.valueOf(vuviec50.getLinhVucGiaoThong()),
							String.valueOf(vuviec50.getToChuc()), vuviec50.getKiemDinh(), vuviec50.getTrangThaiNp(),
							vuviec50.getHanhViVpId(), vuviec50.getLuatTcId(), vuviec50.getTuocTuNgay(),
							vuviec50.getTuocDenNgay(), vuviec50.getCapBacChucVu(), vuviec50.getDonViThuTien(),
							vuviec50.getTangVatTraLai(), vuviec50.getTenDonViLap(),
							String.valueOf(vuviec50.getLoaiTvtgId()), vuviec50.getLoaiTvtg(), vuviec50.getHangTvtg(),
							vuviec50.getNoiCapTvtg(), vuviec50.getHieuLucTvtg(), vuviec50.getTinhTrang(),
							String.valueOf(vuviec50.getSoLuong()), vuviec50.getDonViTinh(), vuviec50.getSoTvtg(),
							vuviec50.getNoiDungHvvp(), vuviec50.getDieuLuat(), vuviec50.getThoiHanTu(),
							vuviec50.getThoiHanDen(), vuviec50.getTangVat() }, count1);

				}
				final ViewBaocaothBb60DAO dao60 = new ViewBaocaothBb60DAO();
				final List<ViewBaocaothBb60> listVuViec60;
				listVuViec60 = dao60.BaoCaoTongHop(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH);
				for (final ViewBaocaothBb60 vuviec60 : listVuViec60) {
					count1++;
					table.addItem(new Object[] { String.valueOf(listVuViec60.indexOf(vuviec60) + 1),
							vuviec60.getMaVuViec(), vuviec60.getMaRutgon(), String.valueOf(vuviec60.getLoaiBbQd()),
							vuviec60.getSoBienBan(), vuviec60.getTenNguoiNvp(),
							String.valueOf(vuviec60.getDiaDanhHcId()), vuviec60.getDiaChiNvp(),
							vuviec60.getNgaySinhNvpNhap(), vuviec60.getNgheNghiepNvp(), vuviec60.getLoaiPhuongTien(),
							vuviec60.getNoiDungVphc(), vuviec60.getBienKiemSoat(), vuviec60.getHangGplx(),
							vuviec60.getGplx(), vuviec60.getThoiGianVphc(), vuviec60.getDiaDiemVphc(),
							vuviec60.getTangVatTg(), vuviec60.getThoiHanTg(), vuviec60.getHinhThucXp(),
							vuviec60.getTongMucPhat(), vuviec60.getXuPhatBoSung(), vuviec60.getTuNgayXpbs(),
							vuviec60.getDenNgayXpbs(), vuviec60.getBienPhapKhacPhuc(), vuviec60.getNgayLapBb(),
							vuviec60.getTenDonVi(), vuviec60.getTenCanBo(),
							vuviec60.getLinhVucGiaoThong() == null ? ""
									: String.valueOf(vuviec60.getLinhVucGiaoThong()),
							String.valueOf(vuviec60.getToChuc()), vuviec60.getKiemDinh(), vuviec60.getTrangThaiNp(),
							vuviec60.getHanhViVpId(), vuviec60.getLuatTcId(), vuviec60.getTuocTuNgay(),
							vuviec60.getTuocDenNgay(), vuviec60.getCapBacChucVu(), vuviec60.getDonViThuTien(),
							vuviec60.getTangVatTraLai(), vuviec60.getTenDonViLap(),
							String.valueOf(vuviec60.getLoaiTvtgId()), vuviec60.getLoaiTvtg(), vuviec60.getHangTvtg(),
							vuviec60.getNoiCapTvtg(), vuviec60.getHieuLucTvtg(), vuviec60.getTinhTrang(),
							String.valueOf(vuviec60.getSoLuong()), vuviec60.getDonViTinh(), vuviec60.getSoTvtg(),
							vuviec60.getNoiDungHvvp(), vuviec60.getDieuLuat(), vuviec60.getThoiHanTu(),
							vuviec60.getThoiHanDen(), vuviec60.getTangVat() }, count1);

				}
				// final ViewBaocaothQd01DAO dao01 = new ViewBaocaothQd01DAO();
				// final List<ViewBaocaothQd01> listVuViec01;
				// listVuViec01 = dao01.BaoCaoTongHop(this.danhSachBCTH,
				// this.fromdateBCTH, this.todateBCTH);

				final ViewBaocaothQd02DAO dao02 = new ViewBaocaothQd02DAO();
				final List<ViewBaocaothQd02> listVuViec02;
				listVuViec02 = dao02.BaoCaoTongHop(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH);
				for (final ViewBaocaothQd02 vuviec02 : listVuViec02) {
					final String stt = String.valueOf(listVuViec02.indexOf(vuviec02) + 1);
					count1++;
					table.addItem(new Object[] { stt, String.valueOf(vuviec02.getMaVuViec()),
							String.valueOf(vuviec02.getMaRutgon()), String.valueOf(vuviec02.getLoaiBbQd()),
							String.valueOf(vuviec02.getSoBienBan()), String.valueOf(vuviec02.getTenNguoiNvp()),
							String.valueOf(vuviec02.getDiaDanhHcId()), vuviec02.getDiaChiNvp(),
							vuviec02.getNgaySinhNvpNhap(), vuviec02.getNgheNghiepNvp(), vuviec02.getLoaiPhuongTien(),
							vuviec02.getNoiDungVphc(), vuviec02.getBienKiemSoat(), vuviec02.getHangGplx(),
							vuviec02.getGplx(), vuviec02.getThoiGianVphc(), vuviec02.getDiaDiemVphc(),
							vuviec02.getTangVatTg(), vuviec02.getThoiHanTg(), vuviec02.getHinhThucXp(),
							vuviec02.getTongMucPhat() == null ? null : (String.valueOf(vuviec02.getTongMucPhat())),
							vuviec02.getXuPhatBoSung(),
							vuviec02.getTuNgayXpbs() == null ? "" : (String.valueOf(vuviec02.getTuNgayXpbs())),
							vuviec02.getDenNgayXpbs() == null ? "" : (String.valueOf(vuviec02.getDenNgayXpbs())),
							vuviec02.getBienPhapKhacPhuc(), vuviec02.getNgayLapBb(), vuviec02.getTenDonVi(),
							vuviec02.getTenCanBo(),
							vuviec02.getLinhVucGiaoThong() == null ? ""
									: String.valueOf(vuviec02.getLinhVucGiaoThong()),
							String.valueOf(vuviec02.getToChuc()), vuviec02.getKiemDinh(),
							vuviec02.getTrangThaiNp() == null ? "" : String.valueOf(vuviec02.getTrangThaiNp()),
							vuviec02.getHanhViVpId() == null ? "" : String.valueOf(vuviec02.getHanhViVpId()),
							vuviec02.getLuatTcId() == null ? "" : String.valueOf(vuviec02.getLuatTcId()),
							vuviec02.getTuocTuNgay() == null ? "" : (String.valueOf(vuviec02.getTuocTuNgay())),
							vuviec02.getTuocDenNgay() == null ? "" : (String.valueOf(vuviec02.getTuocDenNgay())),
							vuviec02.getCapBacChucVu(), vuviec02.getDonViThuTien(), vuviec02.getTangVatTraLai(),
							vuviec02.getTenDonViLap(), vuviec02.getLoaiTvtgId(), vuviec02.getLoaiTvtg(),
							vuviec02.getHangTvtg(), vuviec02.getNoiCapTvtg(), vuviec02.getHieuLucTvtg(),
							vuviec02.getTinhTrang(), vuviec02.getSoLuong(), vuviec02.getDonViTinh(),
							vuviec02.getSoTvtg(), vuviec02.getNoiDungHvvp(), vuviec02.getDieuLuat(),
							vuviec02.getThoiHanTu(), vuviec02.getThoiHanDen(), vuviec02.getTangVat() }, count1);

				}
				final ViewBaocaothQd18DAO dao18 = new ViewBaocaothQd18DAO();
				final List<ViewBaocaothQd18> listVuViec18;
				listVuViec18 = dao18.BaoCaoTongHop(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH);
				for (final ViewBaocaothQd18 vuviec18 : listVuViec18) {
					count1++;
					table.addItem(new Object[] { String.valueOf(listVuViec18.indexOf(vuviec18) + 1),
							String.valueOf(vuviec18.getMaVuViec()), String.valueOf(vuviec18.getMaRutgon()),
							String.valueOf(vuviec18.getLoaiBbQd()), String.valueOf(vuviec18.getSoBienBan()),
							String.valueOf(vuviec18.getTenNguoiNvp()), String.valueOf(vuviec18.getDiaDanhHcId()),
							vuviec18.getDiaChiNvp(), vuviec18.getNgaySinhNvpNhap(), vuviec18.getNgheNghiepNvp(),
							vuviec18.getLoaiPhuongTien(), vuviec18.getNoiDungVphc(), vuviec18.getBienKiemSoat(),
							vuviec18.getHangGplx(), vuviec18.getGplx(), vuviec18.getThoiGianVphc(),
							vuviec18.getDiaDiemVphc(), vuviec18.getTangVatTg(), vuviec18.getThoiHanTg(),
							vuviec18.getHinhThucXp(), vuviec18.getTongMucPhat(), vuviec18.getXuPhatBoSung(),
							vuviec18.getTuNgayXpbs(), vuviec18.getDenNgayXpbs(), vuviec18.getBienPhapKhacPhuc(),
							vuviec18.getNgayLapBb(), vuviec18.getTenDonVi(), vuviec18.getTenCanBo(),
							vuviec18.getLinhVucGiaoThong() == null ? ""
									: String.valueOf(vuviec18.getLinhVucGiaoThong()),
							String.valueOf(vuviec18.getToChuc()), vuviec18.getKiemDinh(), vuviec18.getTrangThaiNp(),
							vuviec18.getHanhViVpId(), vuviec18.getLuatTcId(), vuviec18.getTuocTuNgay(),
							vuviec18.getTuocDenNgay(), vuviec18.getCapBacChucVu(), vuviec18.getDonViThuTien(),
							vuviec18.getTangVatTraLai(), vuviec18.getTenDonViLap(),
							String.valueOf(vuviec18.getLoaiTvtgId()), vuviec18.getLoaiTvtg(), vuviec18.getHangTvtg(),
							vuviec18.getNoiCapTvtg(), vuviec18.getHieuLucTvtg(), vuviec18.getTinhTrang(),
							String.valueOf(vuviec18.getSoLuong()), vuviec18.getDonViTinh(), vuviec18.getSoTvtg(),
							vuviec18.getNoiDungHvvp(), vuviec18.getDieuLuat(),
							vuviec18.getThoiHanTu() == null ? "" : (String.valueOf(vuviec18.getThoiHanTu())),
							vuviec18.getThoiHanDen() == null ? "" : (String.valueOf(vuviec18.getThoiHanDen())),
							vuviec18.getTangVat() }, count1);
					;

				}
				final ViewBaocaothQd20DAO dao20 = new ViewBaocaothQd20DAO();
				final List<ViewBaocaothQd20> listVuViec20;
				listVuViec20 = dao20.BaoCaoTongHop(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH);
				for (final ViewBaocaothQd20 vuviec20 : listVuViec20) {
					count1++;
					table.addItem(new Object[] { String.valueOf(listVuViec20.indexOf(vuviec20) + 1),
							String.valueOf(vuviec20.getMaVuViec()), String.valueOf(vuviec20.getMaRutgon()),
							String.valueOf(vuviec20.getLoaiBbQd()), String.valueOf(vuviec20.getSoBienBan()),
							String.valueOf(vuviec20.getTenNguoiNvp()), String.valueOf(vuviec20.getDiaDanhHcId()),
							vuviec20.getDiaChiNvp(), vuviec20.getNgaySinhNvpNhap(), vuviec20.getNgheNghiepNvp(),
							vuviec20.getLoaiPhuongTien(), vuviec20.getNoiDungVphc(), vuviec20.getBienKiemSoat(),
							vuviec20.getHangGplx(), vuviec20.getGplx(), vuviec20.getThoiGianVphc(),
							vuviec20.getDiaDiemVphc(), vuviec20.getTangVatTg(), vuviec20.getThoiHanTg(),
							vuviec20.getHinhThucXp(), vuviec20.getTongMucPhat(), vuviec20.getXuPhatBoSung(),
							vuviec20.getTuNgayXpbs(), vuviec20.getDenNgayXpbs(), vuviec20.getBienPhapKhacPhuc(),
							vuviec20.getNgayLapBb(), vuviec20.getTenDonVi(), vuviec20.getTenCanBo(),
							vuviec20.getLinhVucGiaoThong() == null ? ""
									: String.valueOf(vuviec20.getLinhVucGiaoThong()),
							String.valueOf(vuviec20.getToChuc()), vuviec20.getKiemDinh(), vuviec20.getTrangThaiNp(),
							vuviec20.getHanhViVpId(), vuviec20.getLuatTcId(), vuviec20.getTuocTuNgay(),
							vuviec20.getTuocDenNgay(), vuviec20.getCapBacChucVu(), vuviec20.getDonViThuTien(),
							vuviec20.getTangVatTraLai(), vuviec20.getTenDonViLap(),
							String.valueOf(vuviec20.getLoaiTvtgId()), vuviec20.getLoaiTvtg(), vuviec20.getHangTvtg(),
							vuviec20.getNoiCapTvtg(), vuviec20.getHieuLucTvtg(), vuviec20.getTinhTrang(),
							String.valueOf(vuviec20.getSoLuong()), vuviec20.getDonViTinh(), vuviec20.getSoTvtg(),
							vuviec20.getNoiDungHvvp(), vuviec20.getDieuLuat(), vuviec20.getThoiHanTu(),
							vuviec20.getThoiHanDen(), vuviec20.getTangVat() }, count1);

				}
				for (final Object i : table.getItemIds()) {

					final Property propertyMaVV = table.getContainerProperty(i, "MA_RUTGON");
					final Row row43 = sheet.createRow(rowNum++);
					final Cell A5 = row43.createCell(0);
					A5.setCellValue(row43.getRowNum() - 2);
					final Cell B5 = row43.createCell(1);
					B5.setCellValue((String) propertyMaVV.getValue());

					final Property propertyLOAI_BB_QD = table.getContainerProperty(i, "LOAI_BB_QD");
					final long loaiBB = Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim());

					final Cell C5 = row43.createCell(2);
					if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 43) {
						C5.setCellValue("Biên bản vi phạm hành chính");
					} else if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 2) {
						C5.setCellValue("Quyết định xử phạt vi phạm hành chính");
					} else if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 1) {
						C5.setCellValue("Quyết định xử phạt vi phạm hành chính không lập biên bản");
					} else if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 18) {
						C5.setCellValue("Quyết định tạm giữ tang vật phương tiện");
					} else if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 20) {
						C5.setCellValue("Quyết định trả lại tang vật phương tiện");
					} else if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 60) {
						C5.setCellValue("Biên bản trả lại tang vật phương tiện");
					} else if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 50) {
						C5.setCellValue("Biên bản tạm giữ tang vật phương tiện");
					}

					final Property propertySoBB = table.getContainerProperty(i, "SO_BIEN_BAN");
					final Cell D5 = row43.createCell(3);
					D5.setCellValue(propertySoBB.getValue().toString());

					final Property propertyTC = table.getContainerProperty(i, "TO_CHUC");
					final Cell E5 = row43.createCell(4);
					if (propertyTC.getValue() != null) {
						if (Long.parseLong(propertyTC.getValue().toString().trim()) == 0) {
							E5.setCellValue("Cá nhân");
						} else if (Long.parseLong(propertyTC.getValue().toString().trim()) == 1) {
							E5.setCellValue("Tổ chức");
						}
					} else {
						E5.setCellValue("");
					}

					final Property propertyTen = table.getContainerProperty(i, "TEN_NGUOI_NVP");
					final Cell F5 = row43.createCell(5);
					F5.setCellValue((String) propertyTen.getValue());

					final Property propertyThoiGianVphc = table.getContainerProperty(i, "THOI_GIAN_VPHC");
					final Cell G5 = row43.createCell(6);
					G5.setCellValue((String) propertyThoiGianVphc.getValue());

					final Property propertyDiaChi = table.getContainerProperty(i, "DIA_CHI_NVP");
					final Cell H5 = row43.createCell(7);
					H5.setCellValue((String) propertyDiaChi.getValue());

					final Cell I5 = row43.createCell(8);
					I5.setCellValue("");

					final Property propertyNgaySinh = table.getContainerProperty(i, "NGAY_SINH_NVP_NHAP");
					final Cell J5 = row43.createCell(9);
					J5.setCellValue((String) propertyNgaySinh.getValue());
					final Cell K5 = row43.createCell(10);
					K5.setCellValue("");

					final Property propertyNgheNghiep = table.getContainerProperty(i, "NGHE_NGHIEP_NVP");
					final Cell L5 = row43.createCell(11);
					L5.setCellValue((String) propertyNgheNghiep.getValue());

					final Property propertylpt = table.getContainerProperty(i, "LOAI_PHUONG_TIEN");
					final Cell M5 = row43.createCell(12);
					M5.setCellValue((String) propertylpt.getValue());

					final Property propertyBKS = table.getContainerProperty(i, "BIEN_KIEM_SOAT");
					final Cell N5 = row43.createCell(13);
					N5.setCellValue((String) propertyBKS.getValue());

					final Property propertydiaDiemVPHC = table.getContainerProperty(i, "DIA_DIEM_VPHC");
					final Cell O5 = row43.createCell(14);
					O5.setCellValue((String) propertydiaDiemVPHC.getValue());

					final Property propertyNoiDungVphc = table.getContainerProperty(i, "NOI_DUNG_VPHC");
					final Cell P5 = row43.createCell(15);
					final int count = 0;
					String NoiDung = "";
					if (propertyNoiDungVphc.getValue() == null) {
						NoiDung = "";
					} else {
						NoiDung = propertyNoiDungVphc.getValue().toString().trim();
						if (NoiDung.contains("quy định tại") == true) {
							P5.setCellValue(substrFromNghiD(typefile(NoiDung)));
						} else {
							P5.setCellValue(NoiDung);
						}
					}

					String sbHvvp = "";
					final Cell Q5 = row43.createCell(16);
					String NoiDungHV = "";
					if (propertyNoiDungVphc.getValue() == null) {
						NoiDungHV = "";
					} else {
						NoiDungHV = propertyNoiDungVphc.getValue().toString().trim();
						if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) != 2
								&& Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) != 1) {
							if (NoiDungHV.contains("quy định tại") == true) {
								sbHvvp = SubStrLuat(NoiDungHV);
							} else {
								sbHvvp = NoiDungHV;
							}
						} else {
							sbHvvp = "";
						}
					}

					final Property propertyDieuLuat = table.getContainerProperty(i, "DIEU_LUAT");
					Q5.setCellValue((String) propertyDieuLuat.getValue());

					final Cell R5 = row43.createCell(17);
					R5.setCellValue(tenNhom);

					final Property propertyTvTg = table.getContainerProperty(i, "TANG_VAT_TG");
					String tangVat = "";
					if (propertyTvTg.getValue() == null) {
						tangVat = "";
					} else {
						tangVat = propertyTvTg.getValue().toString().trim();
						if (tangVat.contains("nơi cấp") == true) {
							substr(tangVat);
						} else {
							tangVat = "";
						}
					}

					final Cell S5 = row43.createCell(18);
					S5.setCellValue((String) propertyTvTg.getValue());

					final Property propertyHangGP = table.getContainerProperty(i, "HANG_GPLX");
					final Cell T5 = row43.createCell(19);
					if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 43) {
						T5.setCellValue((String) propertyHangGP.getValue());
					} else if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) != 43) {
						if (propertyTvTg.getValue() != null) {
							if (propertyTvTg.getValue().toString().contains("giấy phép lái xe")) {
								final String soGPLX = propertyTvTg.getValue().toString().split(" hạng ", 2)[1].trim();
								if (soGPLX.contains("số")) {
									final String hanggphep = soGPLX.split("số", 2)[0].trim();
									T5.setCellValue(hanggphep);
								}
							}
						}
					}

					final Property propertyGP = table.getContainerProperty(i, "GPLX");
					final Property propertyKD = table.getContainerProperty(i, "KIEM_DINH");
					final Cell U5 = row43.createCell(20);
					String gplx = "";
					String kiemdinh = "";
					final String dkyxe = "";
					String giayto = "";
					if (loaiBB == 43) {
						if (propertyGP.getValue() != null) {
							gplx = "Số GPLX: " + propertyGP.getValue().toString().trim();
						}
						if (propertyKD.getValue() != null) {
							kiemdinh = "Số giấy kiểm định: " + propertyKD.getValue().toString().trim();
						}
						giayto = (propertyGP.getValue() == null ? "" : (gplx + "; "))
								+ (propertyKD.getValue() == null ? "" : (kiemdinh + "; "));
					} else if (loaiBB != 43) {
						if (propertyTvTg.getValue() != null) {
							String gphep = "";
							String kiemDinh = "";
							String DkyXe = "";
							if (propertyTvTg.getValue().toString().contains("Khác")) {
								final String TangVat = propertyTvTg.getValue().toString().split("Khác", 2)[0];
								if (TangVat.contains("giấy phép lái xe")) {
									final String soGPLX = TangVat.split("số", 2)[1];
									if (soGPLX.contains(",")) {
										gphep = "Số GPLX: " + soGPLX.split(",", 2)[0].trim();
									} else if ((!soGPLX.contains(",")) && (soGPLX.contains("giá trị đến")
											&& (!(soGPLX.contains("có giá trị đến"))))) {
										final int countGiaTri = soGPLX.split("giá trị đến").length;
										gphep = "Số GPLX: " + soGPLX.split("giá trị đến", countGiaTri)[0].trim();
									} else if ((!soGPLX.contains("giá trị đến")) && (soGPLX.contains("nơi cấp"))) {
										final int countGiaTri = soGPLX.split("nơi cấp").length;
										gphep = "Số GPLX: " + soGPLX.split("nơi cấp", countGiaTri)[0].trim();

									}
								}

								if (TangVat.contains("giấy đăng ký xe")) {
									final String Dki = TangVat.split("giấy đăng ký xe", 2)[1].trim();
									if (Dki.contains(":")) {
										final String soGPLX = Dki.split(":", 2)[1].trim();
										if (soGPLX.contains(",")) {
											DkyXe = "Số đăng ký xe: " + soGPLX.split(",", 2)[0].trim();
										} else if ((!soGPLX.contains(",")) && (soGPLX.contains("nơi cấp"))) {
											final int countGiaTri = soGPLX.split("nơi cấp").length;
											DkyXe = "Số đăng ký xe: " + soGPLX.split("nơi cấp", countGiaTri)[0].trim();

										}
									}
								}
								if (TangVat
										.contains("giấy chứng nhận kiểm định an toàn kỹ thuật và bảo vệ môi trường")) {
									final String Dki = TangVat.split(
											"giấy chứng nhận kiểm định an toàn kỹ thuật và bảo vệ môi trường", 2)[1]
													.trim();
									if (Dki.contains(":")) {
										final int countDau = Dki.split(":").length;
										final String soGPLX = Dki.split(":", countDau)[1].trim();
										if (soGPLX.contains(",")) {
											kiemDinh = "Số giấy kiểm định: " + soGPLX.split(",", 2)[0].trim();
										} else if ((!soGPLX.contains(",")) && (soGPLX.contains("có giá trị đến"))) {
											kiemDinh = "Số giấy kiểm định: "
													+ soGPLX.split("có giá trị đến", 2)[0].trim();
										} else if ((!soGPLX.contains("có giá trị đến")) && (soGPLX.contains(";"))) {
											final int countGiaTri = soGPLX.split(";").length;
											kiemDinh = "Số giấy kiểm định: " + soGPLX.split(";", countGiaTri)[0].trim();

										}
									}
								}
							} else {
								if (propertyTvTg.getValue().toString().contains("giấy phép lái xe")) {
									final String soGPLX = propertyTvTg.getValue().toString().split("số", 2)[1];
									if (soGPLX.contains(";")) {
										final int countDau = soGPLX.split(";").length;
										final String GiayPhep = soGPLX.split(";", countDau)[0].trim();
										if (GiayPhep.contains(",")) {
											gphep = "Số GPLX: " + GiayPhep.split(",", 2)[0].trim();
											final int countGiaTri = GiayPhep.split("giá trị đến").length;
											gphep = "Số GPLX: " + GiayPhep.split("giá trị đến", countGiaTri)[0].trim();
										} else if ((!soGPLX.contains("giá trị đến"))
												&& (GiayPhep.contains("nơi cấp"))) {
											final int countGiaTri = GiayPhep.split("nơi cấp").length;
											gphep = "Số GPLX: " + GiayPhep.split("nơi cấp", countGiaTri)[0].trim();

										}
									}
								}

								if (propertyTvTg.getValue().toString().contains("giấy đăng ký xe")) {
									final String Dki = propertyTvTg.getValue().toString().split("giấy đăng ký xe", 2)[1]
											.trim();
									if (Dki.contains(":")) {
										final int countDau = Dki.split(":").length;
										final String soGPLX = Dki.split(":", countDau)[1].trim();
										if (soGPLX.contains(",")) {
											final int countDauP = soGPLX.split(",").length;
											DkyXe = "Số đăng ký xe: " + soGPLX.split(",", countDauP)[0].trim();
										} else if ((!soGPLX.contains(",")) && (soGPLX.contains("nơi cấp"))) {
											int countGiaTri = soGPLX.split("nơi cấp").length;
											if (countGiaTri == 1) {
												countGiaTri = 2;
											}
											DkyXe = "Số đăng ký xe: " + soGPLX.split("nơi cấp", countGiaTri)[0].trim();

										}
									}
								}
								if (propertyTvTg.getValue().toString()
										.contains("giấy chứng nhận kiểm định an toàn kỹ thuật và bảo vệ môi trường")) {
									final String Dki = propertyTvTg.getValue().toString().split(
											"giấy chứng nhận kiểm định an toàn kỹ thuật và bảo vệ môi trường", 2)[1]
													.trim();
									if (Dki.contains(":")) {
										final int countDau = Dki.split(":").length;
										final String soGPLX = Dki.split(":", countDau)[1].trim();
										if (soGPLX.contains(",")) {
											kiemDinh = "Số giấy kiểm định: " + soGPLX.split(",", 2)[0].trim();
										} else if ((!(soGPLX.contains(","))) && (soGPLX.contains("có giá trị đến"))) {
											kiemDinh = "Số giấy kiểm định: "
													+ soGPLX.split("có giá trị đến", 2)[0].trim();
										} else if ((!soGPLX.contains("có giá trị đến")) && (soGPLX.contains(";"))) {
											final int countGiaTri = soGPLX.split(";").length;
											kiemDinh = "Số giấy kiểm định: " + soGPLX.split(";", countGiaTri)[0].trim();

										}
									}
								}
							}
							giayto = (gphep == "" ? "" : (gphep + "; ")) + (DkyXe == "" ? "" : (DkyXe + "; "))
									+ (kiemDinh == "" ? "" : (kiemDinh + "; "));
						}

					}
					U5.setCellValue(giayto.trim());

					final Cell V5 = row43.createCell(21);
					String finalValueGiatri = "";
					if (propertyTvTg.getValue() != null) {
						if (propertyTvTg.getValue().toString().contains("giá trị đến")
								&& (!propertyTvTg.getValue().toString().contains("có giá trị đến"))) {
							final int Dem = propertyTvTg.getValue().toString().split("giá trị đến").length;
							final String giatriDen = propertyTvTg.getValue().toString().split("giá trị đến", Dem)[1]
									.trim();
							if (giatriDen.contains(";")) {
								int countSplit = giatriDen.split(";").length;
								if (countSplit == 1) {
									countSplit = 2;
								}
								final String giatri = giatriDen.split(";", countSplit)[0].trim();
								if (giatri.length() <= 10) {
									for (int j = giatri.length() - 1; j >= 0; j--) {
										if (giatri.charAt(j) != ';') {
											finalValueGiatri = giatri.charAt(j) + finalValueGiatri;
										} else {
											break;
										}
									}
								} else if (giatri.length() > 10) {
									finalValueGiatri = giatriDen.substring(0, 10).trim();
								}
							}
						} else if (propertyTvTg.getValue().toString().contains("có giá trị đến")) {
							final int Dem = propertyTvTg.getValue().toString().split("có giá trị đến").length;
							final String giatriDen = propertyTvTg.getValue().toString().split("có giá trị đến", Dem)[1]
									.trim();
							String GiaTriWithoutDau = "";
							if (giatriDen.contains(":")) {
								int count2Dots = giatriDen.split(":").length;
								if (count2Dots == 1) {
									count2Dots = 2;
								}
								GiaTriWithoutDau = giatriDen.split(":", count2Dots)[1].trim();
							} else if (!giatriDen.contains(":")) {
								GiaTriWithoutDau = giatriDen;
							}
							if (GiaTriWithoutDau.contains(";")) {
								final int countSplit = GiaTriWithoutDau.split(";").length;
								if (countSplit <= 1) {
									finalValueGiatri = GiaTriWithoutDau;
								} else if (countSplit > 1) {
									final String giatri = GiaTriWithoutDau.split(";", countSplit)[0];
									if (giatri.length() <= 10) {
										for (int j = giatri.length() - 1; j >= 0; j--) {
											if (giatri.charAt(j) != ';') {
												finalValueGiatri = giatri.charAt(j) + finalValueGiatri;
											} else {
												break;
											}
										}
									} else if (giatri.length() > 10) {
										finalValueGiatri = giatri.substring(0, 10).trim();
									}
								}
							}
						}
					}
					V5.setCellValue(finalValueGiatri);

					final Cell W5 = row43.createCell(22);
					if (propertyTvTg.getValue() != null) {
						W5.setCellValue(substr((String) propertyTvTg.getValue()));
					}

					final Property propertyThoiHanTg = table.getContainerProperty(i, "THOI_HAN_TG");
					final Cell X5 = row43.createCell(23);
					X5.setCellValue((String) propertyThoiHanTg.getValue());

					final Property propertyTrHTXP = table.getContainerProperty(i, "HINH_THUC_XP");
					final Cell Y5 = row43.createCell(24);
					Y5.setCellValue((String) propertyTrHTXP.getValue());

					final Property propertyTienNp = table.getContainerProperty(i, "TONG_MUC_PHAT");
					final Cell Z5 = row43.createCell(25);
					final NumberFormat fmMoney = NumberFormat.getCurrencyInstance(new Locale("vn", "VN"));
					final DecimalFormatSymbols formatSym = ((DecimalFormat) fmMoney).getDecimalFormatSymbols();
					formatSym.setCurrencySymbol("");
					((DecimalFormat) fmMoney).setDecimalFormatSymbols(formatSym);
					if (propertyTienNp.getValue() != null && propertyTienNp.getValue() != "") {
						Z5.setCellValue(
								fmMoney.format(Long.parseLong((String) propertyTienNp.getValue())).replace(",", "."));
					}

					final Property propertyTrThaiNp = table.getContainerProperty(i, "TRANG_THAI_NP");
					final Cell AA5 = row43.createCell(26);
					String trangThai = "";
					if (propertyTrThaiNp.getValue() != null && propertyTrThaiNp.getValue() != "") {
						if (Integer.parseInt(propertyTrThaiNp.getValue().toString().trim()) == 3) {
							trangThai = "Đã thanh toán qua DVC";
						} else if (Integer.parseInt(propertyTrThaiNp.getValue().toString().trim()) == 4) {
							trangThai = "Đã gửi SMS";
						} else if (Integer.parseInt(propertyTrThaiNp.getValue().toString().trim()) == 5) {
							trangThai = "Đã thanh toán trực tiếp";
						}
					}
					AA5.setCellValue(trangThai);

					final SimpleDateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");
					final Property propertyXpbs = table.getContainerProperty(i, "XU_PHAT_BO_SUNG");
					final Cell AB5 = row43.createCell(27);
					AB5.setCellValue((String) propertyXpbs.getValue());

					final Property PptuNgayXpbs = table.getContainerProperty(i, "TU_NGAY_XPBS");
					final Cell AC5 = row43.createCell(28);
					String FromDateXpbs = "";
					if (PptuNgayXpbs.getValue() != null && PptuNgayXpbs.getValue() != "") {
						FromDateXpbs = (String) PptuNgayXpbs.getValue();
						final DateFormat formatDateXpbs = new SimpleDateFormat("yyyy-MM-dd");
						Date dateXpbs;
						try {
							dateXpbs = formatDateXpbs.parse(FromDateXpbs);
							AC5.setCellValue(dfm.format(dateXpbs));
						} catch (final ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					final Property PpDenNgayXpbs = table.getContainerProperty(i, "DEN_NGAY_XPBS");
					final Cell AD5 = row43.createCell(29);
					String ToDateXpbs = "";
					if (PpDenNgayXpbs.getValue() != null && PpDenNgayXpbs.getValue() != "") {
						ToDateXpbs = (String) PpDenNgayXpbs.getValue();
						final DateFormat formatDateXpbs = new SimpleDateFormat("yyyy-MM-dd");
						Date dateXpbs;
						try {
							dateXpbs = formatDateXpbs.parse(ToDateXpbs);
							AD5.setCellValue(dfm.format(dateXpbs));
						} catch (final ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					final Property PpBPKP = table.getContainerProperty(i, "BIEN_PHAP_KHAC_PHUC");
					final Cell AE5 = row43.createCell(30);
					AE5.setCellValue((String) PpBPKP.getValue());

					final Property PpTvtl = table.getContainerProperty(i, "TANG_VAT_TRA_LAI");
					final Cell AT5 = row43.createCell(31);
					AT5.setCellValue((String) PpTvtl.getValue());

					final Property PpNgayLapBB = table.getContainerProperty(i, "NGAY_LAP_BB");
					final Cell AF5 = row43.createCell(32);
					Date newDate;
					if (PpNgayLapBB.getValue() != null) {
						newDate = (Date) PpNgayLapBB.getValue();
						AF5.setCellValue(dfm.format(newDate));
					}

					final Property PpTenDvLap = table.getContainerProperty(i, "TEN_DON_VI_LAP");
					final Cell AM5 = row43.createCell(33);
					AM5.setCellValue((String) PpTenDvLap.getValue());

					final Property PpTenDv = table.getContainerProperty(i, "TEN_DON_VI");
					final Cell AG5 = row43.createCell(34);
					AG5.setCellValue((String) PpTenDv.getValue());

					final Property PpTenCb = table.getContainerProperty(i, "TEN_CAN_BO");
					final Cell AH5 = row43.createCell(35);
					AH5.setCellValue((String) PpTenCb.getValue());

					final Property PpCbcv = table.getContainerProperty(i, "CAP_BAC_CHUC_VU");
					final Cell AI5 = row43.createCell(36);
					String chucvu = "";
					if (PpCbcv.getValue() != null) {
						if (PpCbcv.getValue().toString().trim().contains(",")) {
							chucvu = PpCbcv.getValue().toString().trim().split(",", 2)[1].trim();
						}
					}
					AI5.setCellValue(chucvu);
					final Cell AJ5 = row43.createCell(37);
					AJ5.setCellValue(PpTenCb.getValue().toString().trim());
					final Cell AK5 = row43.createCell(38);
					AK5.setCellValue(this.diaBanVp);

					final Property PpLinhVuc = table.getContainerProperty(i, "LINH_VUC_GIAO_THONG");
					final Cell AL5 = row43.createCell(39);
					String linhVucGt = "";
					if (PpLinhVuc.getValue() != "") {
						if (Integer.parseInt(PpLinhVuc.getValue().toString().trim()) == 1) {
							linhVucGt = "Đường bộ";
						} else if (Integer.parseInt(PpLinhVuc.getValue().toString().trim()) == 2) {
							linhVucGt = "Đường sắt";
						} else if (Integer.parseInt(PpLinhVuc.getValue().toString().trim()) == 4) {
							linhVucGt = "Đường thủy";
						}
					}
					AL5.setCellValue(linhVucGt);
				}

			} else {
				if ((this.cmbNhomHv.isEmpty() || this.cmbNhomHv.getValue() == "Tất cả") && this.cmbHvvp.isEmpty()
						&& (this.cmbLoaiGiayTo.isEmpty() || this.cmbLoaiGiayTo.getValue() == "Tất cả")
						&& this.SoGiayToBCTH == "") {

					final ViewBaocaothQd01DAO dao01 = new ViewBaocaothQd01DAO();
					final List<ViewBaocaothQd01> listVuViec01;
					listVuViec01 = dao01.BaoCaoTheoTieuChu(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH,
							toChuc, linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt, loaiGiayTo, hinhThucXp,
							fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH, this.nhomhvKhacBCTH, this.hanhViVPBCTH,
							xpbs, tuocFrom, tuocTo, chucVu, khoBac, nghiDinh, this.tenNvpBCTH, this.DiaChiNvpBCTH,
							this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH,
							this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH);

					for (final ViewBaocaothQd01 vuviec01 : listVuViec01) {
						count1++;
						table.addItem(new Object[] { String.valueOf(listVuViec01.indexOf(vuviec01) + 1),
								String.valueOf(vuviec01.getMaVuViec()), String.valueOf(vuviec01.getMaRutgon()),
								String.valueOf(vuviec01.getLoaiBbQd()), String.valueOf(vuviec01.getSoBienBan()),
								String.valueOf(vuviec01.getTenNguoiNvp()), String.valueOf(vuviec01.getDiaDanhHcId()),
								vuviec01.getDiaChiNvp(), vuviec01.getNgaySinhNvpNhap(), vuviec01.getNgheNghiepNvp(),
								vuviec01.getLoaiPhuongTien(), vuviec01.getNoiDungVphc(), vuviec01.getBienKiemSoat(),
								vuviec01.getHangGplx(), vuviec01.getGplx(), vuviec01.getThoiGianVphc(),
								vuviec01.getDiaDiemVphc(), vuviec01.getTangVatTg(), vuviec01.getThoiHanTg(),
								vuviec01.getHinhThucXp(),
								vuviec01.getTongMucPhat() == null ? null : (String.valueOf(vuviec01.getTongMucPhat())),
								vuviec01.getXuPhatBoSung(),
								vuviec01.getTuNgayXpbs() == null ? "" : (String.valueOf(vuviec01.getTuNgayXpbs())),
								vuviec01.getDenNgayXpbs() == null ? "" : (String.valueOf(vuviec01.getDenNgayXpbs())),
								vuviec01.getBienPhapKhacPhuc(), vuviec01.getNgayLapBb(), vuviec01.getTenDonVi(),
								vuviec01.getTenCanBo(),
								vuviec01.getLinhVucGiaoThong() == null ? ""
										: String.valueOf(vuviec01.getLinhVucGiaoThong()),
								String.valueOf(vuviec01.getToChuc()), vuviec01.getKiemDinh(),
								vuviec01.getTrangThaiNp() == null ? "" : String.valueOf(vuviec01.getTrangThaiNp()),
								vuviec01.getHanhViVpId() == null ? "" : String.valueOf(vuviec01.getHanhViVpId()),
								vuviec01.getLuatTcId() == null ? "" : String.valueOf(vuviec01.getLuatTcId()),
								vuviec01.getTuocTuNgay() == null ? "" : (String.valueOf(vuviec01.getTuocTuNgay())),
								vuviec01.getTuocDenNgay() == null ? "" : (String.valueOf(vuviec01.getTuocDenNgay())),
								vuviec01.getCapBacChucVu(), vuviec01.getDonViThuTien(), vuviec01.getTangVatTraLai(),
								vuviec01.getTenDonViLap(), vuviec01.getLoaiTvtgId(), vuviec01.getLoaiTvtg(),
								vuviec01.getHangTvtg(), vuviec01.getNoiCapTvtg(), vuviec01.getHieuLucTvtg(),
								vuviec01.getTinhTrang(), vuviec01.getSoLuong(), vuviec01.getDonViTinh(),
								vuviec01.getSoTvtg(), vuviec01.getNoiDungHvvp(), vuviec01.getDieuLuat(),
								vuviec01.getThoiHanTu(), vuviec01.getThoiHanDen(), vuviec01.getTangVat() }, count1);

					}

					final ViewBaocaothBb43DAO dao = new ViewBaocaothBb43DAO();
					final List<ViewBaocaothBb43> listVuViec43;
					listVuViec43 = dao.BaoCaoTheoTieuChu(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH, toChuc,
							linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt, loaiGiayTo, hinhThucXp, fromMoney,
							toMoney, hinhThucNp, this.nhomhvBCTH, this.nhomhvKhacBCTH, this.hanhViVPBCTH, xpbs,
							tuocFrom, tuocTo, chucVu, khoBac, nghiDinh, this.tenNvpBCTH, this.DiaChiNvpBCTH,
							this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH,
							this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH);
					for (final ViewBaocaothBb43 vuviec43 : listVuViec43) {
						count1++;
						table.addItem(new Object[] { String.valueOf(listVuViec43.indexOf(vuviec43) + 1),
								vuviec43.getMaVuViec(), vuviec43.getMaRutgon(), String.valueOf(vuviec43.getLoaiBbQd()),
								vuviec43.getSoBienBan(), vuviec43.getTenNguoiNvp(),
								String.valueOf(vuviec43.getDiaDanhHcId()), vuviec43.getDiaChiNvp(),
								vuviec43.getNgaySinhNvpNhap(), vuviec43.getNgheNghiepNvp(),
								vuviec43.getLoaiPhuongTien(), vuviec43.getNoiDungVphc(), vuviec43.getBienKiemSoat(),
								vuviec43.getHangGplx(), vuviec43.getGplx(), vuviec43.getThoiGianVphc(),
								vuviec43.getDiaDiemVphc(), vuviec43.getTangVatTg(), vuviec43.getThoiHanTg(),
								vuviec43.getHinhThucXp(),
								vuviec43.getTongMucPhat() == null ? "" : String.valueOf(vuviec43.getTongMucPhat()),
								vuviec43.getXuPhatBoSung(), vuviec43.getTuNgayXpbs(), vuviec43.getDenNgayXpbs(),
								vuviec43.getBienPhapKhacPhuc(), vuviec43.getNgayLapBb(), vuviec43.getTenDonVi(),
								vuviec43.getTenCanBo(),
								vuviec43.getLinhVucGiaoThong() == null ? ""
										: String.valueOf(vuviec43.getLinhVucGiaoThong()),
								String.valueOf(vuviec43.getToChuc()), vuviec43.getKiemDinh(), vuviec43.getTrangThaiNp(),
								String.valueOf(vuviec43.getHanhViVpId()), String.valueOf(vuviec43.getLuatTcId()),
								vuviec43.getTuocTuNgay() == null ? "" : (String.valueOf(vuviec43.getTuocTuNgay())),
								vuviec43.getTuocDenNgay() == null ? "" : (String.valueOf(vuviec43.getTuocDenNgay())),
								vuviec43.getCapBacChucVu(), vuviec43.getDonViThuTien(), vuviec43.getTangVatTraLai(),
								vuviec43.getTenDonViLap(), String.valueOf(vuviec43.getLoaiTvtgId()),
								vuviec43.getLoaiTvtg(), vuviec43.getHangTvtg(), vuviec43.getNoiCapTvtg(),
								vuviec43.getHieuLucTvtg(), vuviec43.getTinhTrang(),
								String.valueOf(vuviec43.getSoLuong()), vuviec43.getDonViTinh(), vuviec43.getSoTvtg(),
								vuviec43.getNoiDungHvvp(), vuviec43.getDieuLuat(), vuviec43.getThoiHanTu(),
								vuviec43.getThoiHanDen(), vuviec43.getTangVat() }, count1);

					}

					final ViewBaocaothBb50DAO dao50 = new ViewBaocaothBb50DAO();
					final List<ViewBaocaothBb50> listVuViec50;
					listVuViec50 = dao50.BaoCaoTheoTieuChu(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH,
							toChuc, linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt, loaiGiayTo, hinhThucXp,
							fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH, this.nhomhvKhacBCTH, this.hanhViVPBCTH,
							xpbs, tuocFrom, tuocTo, chucVu, khoBac, nghiDinh, this.tenNvpBCTH, this.DiaChiNvpBCTH,
							this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH,
							this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH);
					for (final ViewBaocaothBb50 vuviec50 : listVuViec50) {
						count1++;
						table.addItem(
								new Object[] { String.valueOf(listVuViec50.indexOf(vuviec50) + 1),
										vuviec50.getMaVuViec(), vuviec50.getMaRutgon(),
										String.valueOf(vuviec50.getLoaiBbQd()), vuviec50.getSoBienBan(),
										vuviec50.getTenNguoiNvp(), String.valueOf(vuviec50.getDiaDanhHcId()),
										vuviec50.getDiaChiNvp(), vuviec50.getNgaySinhNvpNhap(),
										vuviec50.getNgheNghiepNvp(), vuviec50.getLoaiPhuongTien(),
										vuviec50.getNoiDungVphc(), vuviec50.getBienKiemSoat(), vuviec50.getHangGplx(),
										vuviec50.getGplx(), vuviec50.getThoiGianVphc(), vuviec50.getDiaDiemVphc(),
										vuviec50.getTangVatTg(), vuviec50.getThoiHanTg(), vuviec50.getHinhThucXp(),
										vuviec50.getTongMucPhat(), vuviec50.getXuPhatBoSung(), vuviec50.getTuNgayXpbs(),
										vuviec50.getDenNgayXpbs(), vuviec50.getBienPhapKhacPhuc(),
										vuviec50.getNgayLapBb(), vuviec50.getTenDonVi(), vuviec50.getTenCanBo(),
										vuviec50.getLinhVucGiaoThong() == null ? ""
												: String.valueOf(vuviec50.getLinhVucGiaoThong()),
										String.valueOf(vuviec50.getToChuc()), vuviec50.getKiemDinh(),
										vuviec50.getTrangThaiNp(), vuviec50.getHanhViVpId(), vuviec50.getLuatTcId(),
										vuviec50.getTuocTuNgay(), vuviec50.getTuocDenNgay(), vuviec50.getCapBacChucVu(),
										vuviec50.getDonViThuTien(), vuviec50.getTangVatTraLai(),
										vuviec50.getTenDonViLap(), String.valueOf(vuviec50.getLoaiTvtgId()),
										vuviec50.getLoaiTvtg(), vuviec50.getHangTvtg(), vuviec50.getNoiCapTvtg(),
										vuviec50.getHieuLucTvtg(), vuviec50.getTinhTrang(),
										String.valueOf(vuviec50.getSoLuong()), vuviec50.getDonViTinh(),
										vuviec50.getSoTvtg(), vuviec50.getNoiDungHvvp(), vuviec50.getDieuLuat(),
										vuviec50.getThoiHanTu(), vuviec50.getThoiHanDen(), vuviec50.getTangVat() },
								count1);

					}
					final ViewBaocaothBb60DAO dao60 = new ViewBaocaothBb60DAO();
					final List<ViewBaocaothBb60> listVuViec60;
					listVuViec60 = dao60.BaoCaoTheoTieuChu(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH,
							toChuc, linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt, loaiGiayTo, hinhThucXp,
							fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH, this.nhomhvKhacBCTH, this.hanhViVPBCTH,
							xpbs, tuocFrom, tuocTo, chucVu, khoBac, nghiDinh, this.tenNvpBCTH, this.DiaChiNvpBCTH,
							this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH,
							this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH);
					for (final ViewBaocaothBb60 vuviec60 : listVuViec60) {
						count1++;
						table.addItem(
								new Object[] { String.valueOf(listVuViec60.indexOf(vuviec60) + 1),
										vuviec60.getMaVuViec(), vuviec60.getMaRutgon(),
										String.valueOf(vuviec60.getLoaiBbQd()), vuviec60.getSoBienBan(),
										vuviec60.getTenNguoiNvp(), String.valueOf(vuviec60.getDiaDanhHcId()),
										vuviec60.getDiaChiNvp(), vuviec60.getNgaySinhNvpNhap(),
										vuviec60.getNgheNghiepNvp(), vuviec60.getLoaiPhuongTien(),
										vuviec60.getNoiDungVphc(), vuviec60.getBienKiemSoat(), vuviec60.getHangGplx(),
										vuviec60.getGplx(), vuviec60.getThoiGianVphc(), vuviec60.getDiaDiemVphc(),
										vuviec60.getTangVatTg(), vuviec60.getThoiHanTg(), vuviec60.getHinhThucXp(),
										vuviec60.getTongMucPhat(), vuviec60.getXuPhatBoSung(), vuviec60.getTuNgayXpbs(),
										vuviec60.getDenNgayXpbs(), vuviec60.getBienPhapKhacPhuc(),
										vuviec60.getNgayLapBb(), vuviec60.getTenDonVi(), vuviec60.getTenCanBo(),
										vuviec60.getLinhVucGiaoThong() == null ? ""
												: String.valueOf(vuviec60.getLinhVucGiaoThong()),
										String.valueOf(vuviec60.getToChuc()), vuviec60.getKiemDinh(),
										vuviec60.getTrangThaiNp(), vuviec60.getHanhViVpId(), vuviec60.getLuatTcId(),
										vuviec60.getTuocTuNgay(), vuviec60.getTuocDenNgay(), vuviec60.getCapBacChucVu(),
										vuviec60.getDonViThuTien(), vuviec60.getTangVatTraLai(),
										vuviec60.getTenDonViLap(), String.valueOf(vuviec60.getLoaiTvtgId()),
										vuviec60.getLoaiTvtg(), vuviec60.getHangTvtg(), vuviec60.getNoiCapTvtg(),
										vuviec60.getHieuLucTvtg(), vuviec60.getTinhTrang(),
										String.valueOf(vuviec60.getSoLuong()), vuviec60.getDonViTinh(),
										vuviec60.getSoTvtg(), vuviec60.getNoiDungHvvp(), vuviec60.getDieuLuat(),
										vuviec60.getThoiHanTu(), vuviec60.getThoiHanDen(), vuviec60.getTangVat() },
								count1);

					}

					final ViewBaocaothQd02DAO dao02 = new ViewBaocaothQd02DAO();
					final List<ViewBaocaothQd02> listVuViec02;
					listVuViec02 = dao02.BaoCaoTheoTieuChu(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH,
							toChuc, linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt, loaiGiayTo, hinhThucXp,
							fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH, this.nhomhvKhacBCTH, this.hanhViVPBCTH,
							xpbs, tuocFrom, tuocTo, chucVu, khoBac, nghiDinh, this.tenNvpBCTH, this.DiaChiNvpBCTH,
							this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH,
							this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH);
					for (final ViewBaocaothQd02 vuviec02 : listVuViec02) {
						final String stt = String.valueOf(listVuViec02.indexOf(vuviec02) + 1);
						count1++;
						table.addItem(new Object[] { stt, String.valueOf(vuviec02.getMaVuViec()),
								String.valueOf(vuviec02.getMaRutgon()), String.valueOf(vuviec02.getLoaiBbQd()),
								String.valueOf(vuviec02.getSoBienBan()), String.valueOf(vuviec02.getTenNguoiNvp()),
								String.valueOf(vuviec02.getDiaDanhHcId()), vuviec02.getDiaChiNvp(),
								vuviec02.getNgaySinhNvpNhap(), vuviec02.getNgheNghiepNvp(),
								vuviec02.getLoaiPhuongTien(), vuviec02.getNoiDungVphc(), vuviec02.getBienKiemSoat(),
								vuviec02.getHangGplx(), vuviec02.getGplx(), vuviec02.getThoiGianVphc(),
								vuviec02.getDiaDiemVphc(), vuviec02.getTangVatTg(), vuviec02.getThoiHanTg(),
								vuviec02.getHinhThucXp(),
								vuviec02.getTongMucPhat() == null ? null : (String.valueOf(vuviec02.getTongMucPhat())),
								vuviec02.getXuPhatBoSung(),
								vuviec02.getTuNgayXpbs() == null ? "" : (String.valueOf(vuviec02.getTuNgayXpbs())),
								vuviec02.getDenNgayXpbs() == null ? "" : (String.valueOf(vuviec02.getDenNgayXpbs())),
								vuviec02.getBienPhapKhacPhuc(), vuviec02.getNgayLapBb(), vuviec02.getTenDonVi(),
								vuviec02.getTenCanBo(),
								vuviec02.getLinhVucGiaoThong() == null ? ""
										: String.valueOf(vuviec02.getLinhVucGiaoThong()),
								String.valueOf(vuviec02.getToChuc()), vuviec02.getKiemDinh(),
								vuviec02.getTrangThaiNp() == null ? "" : String.valueOf(vuviec02.getTrangThaiNp()),
								vuviec02.getHanhViVpId() == null ? "" : String.valueOf(vuviec02.getHanhViVpId()),
								vuviec02.getLuatTcId() == null ? "" : String.valueOf(vuviec02.getLuatTcId()),
								vuviec02.getTuocTuNgay() == null ? "" : (String.valueOf(vuviec02.getTuocTuNgay())),
								vuviec02.getTuocDenNgay() == null ? "" : (String.valueOf(vuviec02.getTuocDenNgay())),
								vuviec02.getCapBacChucVu(), vuviec02.getDonViThuTien(), vuviec02.getTangVatTraLai(),
								vuviec02.getTenDonViLap(), vuviec02.getLoaiTvtgId(), vuviec02.getLoaiTvtg(),
								vuviec02.getHangTvtg(), vuviec02.getNoiCapTvtg(), vuviec02.getHieuLucTvtg(),
								vuviec02.getTinhTrang(), vuviec02.getSoLuong(), vuviec02.getDonViTinh(),
								vuviec02.getSoTvtg(), vuviec02.getNoiDungHvvp(), vuviec02.getDieuLuat(),
								vuviec02.getThoiHanTu(), vuviec02.getThoiHanDen(), vuviec02.getTangVat() }, count1);

					}

					final ViewBaocaothQd18DAO dao18 = new ViewBaocaothQd18DAO();
					final List<ViewBaocaothQd18> listVuViec18;
					listVuViec18 = dao18.BaoCaoTheoTieuChu(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH,
							toChuc, linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt, loaiGiayTo, hinhThucXp,
							fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH, this.nhomhvKhacBCTH, this.hanhViVPBCTH,
							xpbs, tuocFrom, tuocTo, chucVu, khoBac, nghiDinh, this.tenNvpBCTH, this.DiaChiNvpBCTH,
							this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH,
							this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH);
					for (final ViewBaocaothQd18 vuviec18 : listVuViec18) {
						count1++;
						table.addItem(new Object[] { String.valueOf(listVuViec18.indexOf(vuviec18) + 1),
								String.valueOf(vuviec18.getMaVuViec()), String.valueOf(vuviec18.getMaRutgon()),
								String.valueOf(vuviec18.getLoaiBbQd()), String.valueOf(vuviec18.getSoBienBan()),
								String.valueOf(vuviec18.getTenNguoiNvp()), String.valueOf(vuviec18.getDiaDanhHcId()),
								vuviec18.getDiaChiNvp(), vuviec18.getNgaySinhNvpNhap(), vuviec18.getNgheNghiepNvp(),
								vuviec18.getLoaiPhuongTien(), vuviec18.getNoiDungVphc(), vuviec18.getBienKiemSoat(),
								vuviec18.getHangGplx(), vuviec18.getGplx(), vuviec18.getThoiGianVphc(),
								vuviec18.getDiaDiemVphc(), vuviec18.getTangVatTg(), vuviec18.getThoiHanTg(),
								vuviec18.getHinhThucXp(), vuviec18.getTongMucPhat(), vuviec18.getXuPhatBoSung(),
								vuviec18.getTuNgayXpbs(), vuviec18.getDenNgayXpbs(), vuviec18.getBienPhapKhacPhuc(),
								vuviec18.getNgayLapBb(), vuviec18.getTenDonVi(), vuviec18.getTenCanBo(),
								vuviec18.getLinhVucGiaoThong() == null ? ""
										: String.valueOf(vuviec18.getLinhVucGiaoThong()),
								String.valueOf(vuviec18.getToChuc()), vuviec18.getKiemDinh(), vuviec18.getTrangThaiNp(),
								vuviec18.getHanhViVpId(), vuviec18.getLuatTcId(), vuviec18.getTuocTuNgay(),
								vuviec18.getTuocDenNgay(), vuviec18.getCapBacChucVu(), vuviec18.getDonViThuTien(),
								vuviec18.getTangVatTraLai(), vuviec18.getTenDonViLap(),
								String.valueOf(vuviec18.getLoaiTvtgId()), vuviec18.getLoaiTvtg(),
								vuviec18.getHangTvtg(), vuviec18.getNoiCapTvtg(), vuviec18.getHieuLucTvtg(),
								vuviec18.getTinhTrang(), String.valueOf(vuviec18.getSoLuong()), vuviec18.getDonViTinh(),
								vuviec18.getSoTvtg(), vuviec18.getNoiDungHvvp(), vuviec18.getDieuLuat(),
								vuviec18.getThoiHanTu() == null ? "" : (String.valueOf(vuviec18.getThoiHanTu())),
								vuviec18.getThoiHanDen() == null ? "" : (String.valueOf(vuviec18.getThoiHanDen())),
								vuviec18.getTangVat() }, count1);
						;

					}

					final ViewBaocaothQd20DAO dao20 = new ViewBaocaothQd20DAO();
					final List<ViewBaocaothQd20> listVuViec20;
					listVuViec20 = dao20.BaoCaoTheoTieuChu(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH,
							toChuc, linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt, loaiGiayTo, hinhThucXp,
							fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH, this.nhomhvKhacBCTH, this.hanhViVPBCTH,
							xpbs, tuocFrom, tuocTo, chucVu, khoBac, nghiDinh, this.tenNvpBCTH, this.DiaChiNvpBCTH,
							this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH,
							this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH);
					for (final ViewBaocaothQd20 vuviec20 : listVuViec20) {
						count1++;
						table.addItem(new Object[] { String.valueOf(listVuViec20.indexOf(vuviec20) + 1),
								String.valueOf(vuviec20.getMaVuViec()), String.valueOf(vuviec20.getMaRutgon()),
								String.valueOf(vuviec20.getLoaiBbQd()), String.valueOf(vuviec20.getSoBienBan()),
								String.valueOf(vuviec20.getTenNguoiNvp()), String.valueOf(vuviec20.getDiaDanhHcId()),
								vuviec20.getDiaChiNvp(), vuviec20.getNgaySinhNvpNhap(), vuviec20.getNgheNghiepNvp(),
								vuviec20.getLoaiPhuongTien(), vuviec20.getNoiDungVphc(), vuviec20.getBienKiemSoat(),
								vuviec20.getHangGplx(), vuviec20.getGplx(), vuviec20.getThoiGianVphc(),
								vuviec20.getDiaDiemVphc(), vuviec20.getTangVatTg(), vuviec20.getThoiHanTg(),
								vuviec20.getHinhThucXp(), vuviec20.getTongMucPhat(), vuviec20.getXuPhatBoSung(),
								vuviec20.getTuNgayXpbs(), vuviec20.getDenNgayXpbs(), vuviec20.getBienPhapKhacPhuc(),
								vuviec20.getNgayLapBb(), vuviec20.getTenDonVi(), vuviec20.getTenCanBo(),
								vuviec20.getLinhVucGiaoThong() == null ? ""
										: String.valueOf(vuviec20.getLinhVucGiaoThong()),
								String.valueOf(vuviec20.getToChuc()), vuviec20.getKiemDinh(), vuviec20.getTrangThaiNp(),
								vuviec20.getHanhViVpId(), vuviec20.getLuatTcId(), vuviec20.getTuocTuNgay(),
								vuviec20.getTuocDenNgay(), vuviec20.getCapBacChucVu(), vuviec20.getDonViThuTien(),
								vuviec20.getTangVatTraLai(), vuviec20.getTenDonViLap(),
								String.valueOf(vuviec20.getLoaiTvtgId()), vuviec20.getLoaiTvtg(),
								vuviec20.getHangTvtg(), vuviec20.getNoiCapTvtg(), vuviec20.getHieuLucTvtg(),
								vuviec20.getTinhTrang(), String.valueOf(vuviec20.getSoLuong()), vuviec20.getDonViTinh(),
								vuviec20.getSoTvtg(), vuviec20.getNoiDungHvvp(), vuviec20.getDieuLuat(),
								vuviec20.getThoiHanTu(), vuviec20.getThoiHanDen(), vuviec20.getTangVat() }, count1);

					}

					// final ViewBaocaothQd01DAO dao01 = new
					// ViewBaocaothQd01DAO();
					// final List<ViewBaocaothQd01> listVuViec01;
					// listVuViec01 = dao01.BaoCaoTheoTieuChu(this.danhSachBCTH,
					// this.fromdateBCTH, this.todateBCTH, toChuc,
					// linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt,
					// loaiGiayTo, hinhThucXp, fromMoney,
					// toMoney, hinhThucNp, this.nhomhvBCTH,
					// this.nhomhvKhacBCTH, this.hanhViVPBCTH, xpbs,
					// tuocFrom, tuocTo, chucVu, khoBac, nghiDinh,
					// this.tenNvpBCTH, this.DiaChiNvpBCTH,
					// this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH,
					// this.BKSBCTH, this.soBBBCTH,
					// this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH,
					// this.thoiHanDenBCTH,
					// this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH,
					// this.XaBCTH, this.QuocLoBCTH,
					// this.TuyenDuongBCTH);

					for (final Object i : table.getItemIds()) {

						final Property propertyMaVV = table.getContainerProperty(i, "MA_RUTGON");
						final Row row43 = sheet.createRow(rowNum++);
						final Cell A5 = row43.createCell(0);
						A5.setCellValue(row43.getRowNum() - 2);
						final Cell B5 = row43.createCell(1);
						B5.setCellValue((String) propertyMaVV.getValue());

						final Property propertyLOAI_BB_QD = table.getContainerProperty(i, "LOAI_BB_QD");
						final long loaiBB = Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim());

						final Cell C5 = row43.createCell(2);
						if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 43) {
							C5.setCellValue("Biên bản vi phạm hành chính");
						} else if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 2) {
							C5.setCellValue("Quyết định xử phạt vi phạm hành chính");
						} else if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 1) {
							C5.setCellValue("Quyết định xử phạt vi phạm hành chính không lập biên bản");
						} else if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 18) {
							C5.setCellValue("Quyết định tạm giữ tang vật phương tiện");
						} else if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 20) {
							C5.setCellValue("Quyết định trả lại tang vật phương tiện");
						} else if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 60) {
							C5.setCellValue("Biên bản trả lại tang vật phương tiện");
						} else if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 50) {
							C5.setCellValue("Biên bản tạm giữ tang vật phương tiện");
						}

						final Property propertySoBB = table.getContainerProperty(i, "SO_BIEN_BAN");
						final Cell D5 = row43.createCell(3);
						D5.setCellValue(propertySoBB.getValue().toString());

						final Property propertyTC = table.getContainerProperty(i, "TO_CHUC");
						final Cell E5 = row43.createCell(4);
						if (propertyTC.getValue() != null) {
							if (Long.parseLong(propertyTC.getValue().toString().trim()) == 0) {
								E5.setCellValue("Cá nhân");
							} else if (Long.parseLong(propertyTC.getValue().toString().trim()) == 1) {
								E5.setCellValue("Tổ chức");
							}
						} else {
							E5.setCellValue("");
						}

						final Property propertyTen = table.getContainerProperty(i, "TEN_NGUOI_NVP");
						final Cell F5 = row43.createCell(5);
						F5.setCellValue((String) propertyTen.getValue());

						final Property propertyThoiGianVphc = table.getContainerProperty(i, "THOI_GIAN_VPHC");
						final Cell G5 = row43.createCell(6);
						G5.setCellValue((String) propertyThoiGianVphc.getValue());

						final Property propertyDiaChi = table.getContainerProperty(i, "DIA_CHI_NVP");
						final Cell H5 = row43.createCell(7);
						H5.setCellValue((String) propertyDiaChi.getValue());

						final Cell I5 = row43.createCell(8);
						I5.setCellValue("");

						final Property propertyNgaySinh = table.getContainerProperty(i, "NGAY_SINH_NVP_NHAP");
						final Cell J5 = row43.createCell(9);
						J5.setCellValue((String) propertyNgaySinh.getValue());
						final Cell K5 = row43.createCell(10);
						K5.setCellValue("");

						final Property propertyNgheNghiep = table.getContainerProperty(i, "NGHE_NGHIEP_NVP");
						final Cell L5 = row43.createCell(11);
						L5.setCellValue((String) propertyNgheNghiep.getValue());

						final Property propertylpt = table.getContainerProperty(i, "LOAI_PHUONG_TIEN");
						final Cell M5 = row43.createCell(12);
						M5.setCellValue((String) propertylpt.getValue());

						final Property propertyBKS = table.getContainerProperty(i, "BIEN_KIEM_SOAT");
						final Cell N5 = row43.createCell(13);
						N5.setCellValue((String) propertyBKS.getValue());

						final Property propertydiaDiemVPHC = table.getContainerProperty(i, "DIA_DIEM_VPHC");
						final Cell O5 = row43.createCell(14);
						O5.setCellValue((String) propertydiaDiemVPHC.getValue());

						final Property propertyNoiDungVphc = table.getContainerProperty(i, "NOI_DUNG_VPHC");
						final Cell P5 = row43.createCell(15);
						final int count = 0;
						String NoiDung = "";
						if (propertyNoiDungVphc.getValue() == null) {
							NoiDung = "";
						} else {
							NoiDung = propertyNoiDungVphc.getValue().toString().trim();
							if (NoiDung.contains("quy định tại") == true) {
								P5.setCellValue(substrFromNghiD(typefile(NoiDung)));
							} else {
								P5.setCellValue(NoiDung);
							}
						}

						String sbHvvp = "";
						final Cell Q5 = row43.createCell(16);
						String NoiDungHV = "";
						if (propertyNoiDungVphc.getValue() == null) {
							NoiDungHV = "";
						} else {
							NoiDungHV = propertyNoiDungVphc.getValue().toString().trim();
							if (loaiBB != 2 && loaiBB != 1) {
								if (NoiDungHV.contains("quy định tại") == true) {
									sbHvvp = SubStrLuat(NoiDungHV);
								} else {
									sbHvvp = NoiDungHV;
								}
							} else {
								sbHvvp = "";
							}
						}

						final Property propertyDieuLuat = table.getContainerProperty(i, "DIEU_LUAT");
						Q5.setCellValue((String) propertyDieuLuat.getValue());

						final Cell R5 = row43.createCell(17);
						R5.setCellValue(tenNhom);

						final Property propertyTvTg = table.getContainerProperty(i, "TANG_VAT_TG");
						String tangVat = "";
						if (propertyTvTg.getValue() == null) {
							tangVat = "";
						} else {
							tangVat = propertyTvTg.getValue().toString().trim();
							if (tangVat.contains("nơi cấp") == true) {
								substr(tangVat);
							} else {
								tangVat = "";
							}
						}
						final Cell S5 = row43.createCell(18);
						S5.setCellValue((String) propertyTvTg.getValue());

						final Property propertyHangGP = table.getContainerProperty(i, "HANG_GPLX");
						final Cell T5 = row43.createCell(19);
						if (loaiBB == 43) {
							T5.setCellValue((String) propertyHangGP.getValue());
						} else if (loaiBB != 43) {
							if (propertyTvTg.getValue() != null) {
								final String tv = (String) propertyTvTg.getValue();
								if (tv.contains("giấy phép lái xe")) {
									final String soGPLX = tv.split(" hạng ", 2)[1].trim();
									if (soGPLX.contains("số")) {
										final String hanggphep = soGPLX.split("số", 2)[0].trim();
										T5.setCellValue(hanggphep);
									}
								}
							}
						}

						final Property propertyGP = table.getContainerProperty(i, "GPLX");
						final Property propertyKD = table.getContainerProperty(i, "KIEM_DINH");
						final Cell U5 = row43.createCell(20);
						String gplx = "";
						String kiemdinh = "";
						final String dkyxe = "";
						String giayto = "";
						if (loaiBB == 43) {
							if (propertyGP.getValue() != null) {
								gplx = "Số GPLX: " + propertyGP.getValue().toString().trim();
							}
							if (propertyKD.getValue() != null) {
								kiemdinh = "Số giấy kiểm định: " + propertyKD.getValue().toString().trim();
							}
							giayto = (propertyGP.getValue() == null ? "" : (gplx + "; "))
									+ (propertyKD.getValue() == null ? "" : (kiemdinh + "; "));
						} else if (loaiBB != 43) {
							if (propertyTvTg.getValue() != null) {
								final String tvtg = (String) propertyTvTg.getValue();
								String gphep = "";
								String kiemDinh = "";
								String DkyXe = "";
								if (tvtg.contains("Khác")) {
									final String TangVat = tvtg.split("Khác", 2)[0];
									if (TangVat.contains("giấy phép lái xe")) {
										final String soGPLX = TangVat.split("số", 2)[1];
										if (soGPLX.contains(",")) {
											gphep = "Số GPLX: " + soGPLX.split(",", 2)[0].trim();
										} else if ((!soGPLX.contains(",")) && (soGPLX.contains("giá trị đến"))) {
											final int countGiaTri = soGPLX.split("giá trị đến").length;
											gphep = "Số GPLX: " + soGPLX.split("giá trị đến", countGiaTri)[0].trim();
										} else if ((!soGPLX.contains("giá trị đến")) && (soGPLX.contains("nơi cấp"))) {
											final int countGiaTri = soGPLX.split("nơi cấp").length;
											gphep = "Số GPLX: " + soGPLX.split("nơi cấp", countGiaTri)[0].trim();

										}
									}

									if (TangVat.contains("giấy đăng ký xe")) {
										final String Dki = TangVat.split("giấy đăng ký xe", 2)[1].trim();
										if (Dki.contains(":")) {
											final String soGPLX = Dki.split(":", 2)[1].trim();
											if (soGPLX.contains(",")) {
												DkyXe = "Số đăng ký xe: " + soGPLX.split(",", 2)[0].trim();
											} else if ((!soGPLX.contains(",")) && (soGPLX.contains("nơi cấp"))) {
												final int countGiaTri = soGPLX.split("nơi cấp").length;
												DkyXe = "Số đăng ký xe: "
														+ soGPLX.split("nơi cấp", countGiaTri)[0].trim();

											}
										}
									}
									if (TangVat.contains(
											"giấy chứng nhận kiểm định an toàn kỹ thuật và bảo vệ môi trường")) {
										final String Dki = TangVat.split(
												"giấy chứng nhận kiểm định an toàn kỹ thuật và bảo vệ môi trường", 2)[1]
														.trim();
										if (Dki.contains(":")) {
											final String soGPLX = Dki.split(":", 2)[1].trim();
											if (soGPLX.contains(",")) {
												kiemDinh = "Số giấy kiểm định: " + soGPLX.split(",", 2)[0].trim();
											} else if ((!soGPLX.contains(",")) && (soGPLX.contains("có giá trị đến"))) {
												kiemDinh = "Số giấy kiểm định: " + soGPLX.split(",", 2)[0].trim();
											} else if ((!soGPLX.contains("có giá trị đến")) && (soGPLX.contains(";"))) {
												final int countGiaTri = soGPLX.split(";").length;
												kiemDinh = "Số giấy kiểm định: "
														+ soGPLX.split(";", countGiaTri)[0].trim();

											}
										}
									}
								} else {
									if (tvtg.contains("giấy phép lái xe")) {
										final String soGPLX = tvtg.split("số", 2)[1];
										if (soGPLX.contains(";")) {
											final int countDau = soGPLX.split(";").length;
											final String GiayPhep = soGPLX.split(";", countDau)[0].trim();
											if (GiayPhep.contains(",")) {
												gphep = "Số GPLX: " + GiayPhep.split(",", 2)[0].trim();
											} else if ((!soGPLX.contains(",")) && (GiayPhep.contains("giá trị đến"))) {
												final int countGiaTri = GiayPhep.split("giá trị đến").length;
												gphep = "Số GPLX: "
														+ GiayPhep.split("giá trị đến", countGiaTri)[0].trim();
											} else if ((!soGPLX.contains("giá trị đến"))
													&& (GiayPhep.contains("nơi cấp"))) {
												final int countGiaTri = GiayPhep.split("nơi cấp").length;
												gphep = "Số GPLX: " + GiayPhep.split("nơi cấp", countGiaTri)[0].trim();

											}
										}
									}

									if (tvtg.contains("giấy đăng ký xe")) {
										final String Dki = tvtg.split("giấy đăng ký xe", 2)[1].trim();
										if (Dki.contains(":")) {
											final int countDau = Dki.split(":").length;
											final String soGPLX = Dki.split(":", countDau)[1].trim();
											if (soGPLX.contains(",")) {
												DkyXe = "Số đăng ký xe: " + soGPLX.split(",", 2)[0].trim();
											} else if ((!soGPLX.contains(",")) && (soGPLX.contains("nơi cấp"))) {
												int countGiaTri = soGPLX.split("nơi cấp").length;
												if (countGiaTri == 1) {
													countGiaTri = 2;
												}
												DkyXe = "Số đăng ký xe: "
														+ soGPLX.split("nơi cấp", countGiaTri)[0].trim();

											}
										}
									}
									if (tvtg.contains(
											"giấy chứng nhận kiểm định an toàn kỹ thuật và bảo vệ môi trường")) {
										final String Dki = tvtg.split(
												"giấy chứng nhận kiểm định an toàn kỹ thuật và bảo vệ môi trường", 2)[1]
														.trim();
										if (Dki.contains(":")) {
											final int countDau = Dki.split(":").length;
											final String soGPLX = Dki.split(":", countDau)[1].trim();
											if (soGPLX.contains(",")) {
												kiemDinh = "Số giấy kiểm định: " + soGPLX.split(",", 2)[0].trim();
											} else if ((!(soGPLX.contains(",")))
													&& (soGPLX.contains("có giá trị đến"))) {
												kiemDinh = "Số giấy kiểm định: "
														+ soGPLX.split("có giá trị đến", 2)[0].trim();
											} else if ((!soGPLX.contains("có giá trị đến")) && (soGPLX.contains(";"))) {
												final int countGiaTri = soGPLX.split(";").length;
												kiemDinh = "Số giấy kiểm định: "
														+ soGPLX.split(";", countGiaTri)[0].trim();

											}
										}
									}
								}
								giayto = (gphep == "" ? "" : (gphep + "; ")) + (DkyXe == "" ? "" : (DkyXe + "; "))
										+ (kiemDinh == "" ? "" : (kiemDinh + "; "));
							}

						}
						U5.setCellValue(giayto.trim());

						final Cell V5 = row43.createCell(21);
						String finalValueGiatri = "";
						if (propertyTvTg.getValue() != null) {
							final String tvtg = (String) propertyTvTg.getValue();
							if (tvtg.contains("giá trị đến") && (!tvtg.contains("có giá trị đến"))) {
								final int Dem = tvtg.toString().split("giá trị đến").length;
								final String giatriDen = tvtg.toString().split("giá trị đến", Dem)[1].trim();
								if (giatriDen.contains(";")) {
									int countSplit = giatriDen.split(";").length;
									if (countSplit == 1) {
										countSplit = 2;
									}
									final String giatri = giatriDen.split(";", countSplit)[0].trim();
									if (giatri.length() <= 10) {
										for (int j = giatri.length() - 1; j >= 0; j--) {
											if (giatri.charAt(j) != ';') {
												finalValueGiatri = giatri.charAt(j) + finalValueGiatri;
											} else {
												break;
											}
										}
									} else if (giatri.length() > 10) {
										finalValueGiatri = giatriDen.substring(0, 10).trim();
									}
								}
							} else if (tvtg.contains("có giá trị đến")) {
								final int Dem = tvtg.toString().split("có giá trị đến").length;
								final String giatriDen = tvtg.toString().split("có giá trị đến", Dem)[1].trim();
								String GiaTriWithoutDau = "";
								if (giatriDen.contains(":")) {
									int count2Dots = giatriDen.split(":").length;
									if (count2Dots == 1) {
										count2Dots = 2;
									}
									GiaTriWithoutDau = giatriDen.split(":", count2Dots)[1].trim();
								} else if (!giatriDen.contains(":")) {
									GiaTriWithoutDau = giatriDen;
								}
								if (GiaTriWithoutDau.contains(";")) {
									final int countSplit = GiaTriWithoutDau.split(";").length;
									if (countSplit == 1) {
										if (GiaTriWithoutDau.length() <= 10) {
											for (int j = GiaTriWithoutDau.length() - 1; j >= 0; j--) {
												if (GiaTriWithoutDau.charAt(j) != ';') {
													finalValueGiatri = GiaTriWithoutDau.charAt(j) + finalValueGiatri;
												} else {
													break;
												}
											}
										} else if (GiaTriWithoutDau.length() > 10) {
											finalValueGiatri = GiaTriWithoutDau.substring(0, 10).trim();
										}
									} else if (countSplit > 1) {
										final String giatri = GiaTriWithoutDau.split(";", countSplit)[0];
										if (giatri.length() <= 10) {
											for (int j = giatri.length() - 1; j >= 0; j--) {
												if (giatri.charAt(j) != ';') {
													finalValueGiatri = giatri.charAt(j) + finalValueGiatri;
												} else {
													break;
												}
											}
										} else if (giatri.length() > 10) {
											finalValueGiatri = giatri.substring(0, 10).trim();
										}
									}
								}
							}
						}
						V5.setCellValue(finalValueGiatri);
						final Cell W5 = row43.createCell(22);
						W5.setCellValue(substr(tangVat));

						final Property propertyThoiHanTg = table.getContainerProperty(i, "THOI_HAN_TG");
						final Cell X5 = row43.createCell(23);
						X5.setCellValue((String) propertyThoiHanTg.getValue());

						final Property propertyTrHTXP = table.getContainerProperty(i, "HINH_THUC_XP");
						final Cell Y5 = row43.createCell(24);
						Y5.setCellValue((String) propertyTrHTXP.getValue());

						final Property propertyTienNp = table.getContainerProperty(i, "TONG_MUC_PHAT");
						final Cell Z5 = row43.createCell(25);
						final NumberFormat fmMoney = NumberFormat.getCurrencyInstance(new Locale("vn", "VN"));
						final DecimalFormatSymbols formatSym = ((DecimalFormat) fmMoney).getDecimalFormatSymbols();
						formatSym.setCurrencySymbol("");
						((DecimalFormat) fmMoney).setDecimalFormatSymbols(formatSym);
						if (propertyTienNp.getValue() != null && propertyTienNp.getValue() != "") {
							Z5.setCellValue(fmMoney.format(Long.parseLong((String) propertyTienNp.getValue()))
									.replace(",", "."));
						}

						final Property propertyTrThaiNp = table.getContainerProperty(i, "TRANG_THAI_NP");
						final Cell AA5 = row43.createCell(26);
						String trangThai = "";
						if (propertyTrThaiNp.getValue() != null && propertyTrThaiNp.getValue() != "") {
							if (Integer.parseInt(propertyTrThaiNp.getValue().toString().trim()) == 3) {
								trangThai = "Đã thanh toán qua DVC";
							} else if (Integer.parseInt(propertyTrThaiNp.getValue().toString().trim()) == 4) {
								trangThai = "Đã gửi SMS";
							} else if (Integer.parseInt(propertyTrThaiNp.getValue().toString().trim()) == 5) {
								trangThai = "Đã thanh toán trực tiếp";
							}
						}
						AA5.setCellValue(trangThai);

						final Property propertyXpbs = table.getContainerProperty(i, "XU_PHAT_BO_SUNG");
						final Cell AB5 = row43.createCell(27);
						AB5.setCellValue((String) propertyXpbs.getValue());

						final SimpleDateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");

						final Property PptuNgayXpbs = table.getContainerProperty(i, "TU_NGAY_XPBS");
						final Cell AC5 = row43.createCell(28);
						String FromDateXpbs = "";
						if (PptuNgayXpbs.getValue() != null && PptuNgayXpbs.getValue() != "") {
							FromDateXpbs = (String) PptuNgayXpbs.getValue();
							final DateFormat formatDateXpbs = new SimpleDateFormat("yyyy-MM-dd");
							Date dateXpbs;
							try {
								dateXpbs = formatDateXpbs.parse(FromDateXpbs);
								AC5.setCellValue(dfm.format(dateXpbs));
							} catch (final ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						final Property PpDenNgayXpbs = table.getContainerProperty(i, "DEN_NGAY_XPBS");
						final Cell AD5 = row43.createCell(29);
						String ToDateXpbs = "";
						if (PpDenNgayXpbs.getValue() != null && PpDenNgayXpbs.getValue() != "") {
							ToDateXpbs = (String) PpDenNgayXpbs.getValue();
							final DateFormat formatDateXpbs = new SimpleDateFormat("yyyy-MM-dd");
							Date dateXpbs;
							try {
								dateXpbs = formatDateXpbs.parse(ToDateXpbs);
								AD5.setCellValue(dfm.format(dateXpbs));
							} catch (final ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						final Property PpBPKP = table.getContainerProperty(i, "BIEN_PHAP_KHAC_PHUC");
						final Cell AE5 = row43.createCell(30);
						AE5.setCellValue((String) PpBPKP.getValue());

						final Property PpTvtl = table.getContainerProperty(i, "TANG_VAT_TRA_LAI");
						final Cell AT5 = row43.createCell(31);
						AT5.setCellValue((String) PpTvtl.getValue());

						final Property PpNgayLapBB = table.getContainerProperty(i, "NGAY_LAP_BB");
						final Cell AF5 = row43.createCell(32);
						Date newDate;
						if (PpNgayLapBB.getValue() != null) {
							newDate = (Date) PpNgayLapBB.getValue();
							AF5.setCellValue(dfm.format(newDate));
						}

						final Property PpTenDvLap = table.getContainerProperty(i, "TEN_DON_VI_LAP");
						final Cell AM5 = row43.createCell(33);
						AM5.setCellValue((String) PpTenDvLap.getValue());

						final Property PpTenDv = table.getContainerProperty(i, "TEN_DON_VI");
						final Cell AG5 = row43.createCell(34);
						AG5.setCellValue((String) PpTenDv.getValue());

						final Property PpTenCb = table.getContainerProperty(i, "TEN_CAN_BO");
						final Cell AH5 = row43.createCell(35);
						AH5.setCellValue((String) PpTenCb.getValue());

						final Property PpCbcv = table.getContainerProperty(i, "CAP_BAC_CHUC_VU");
						final Cell AI5 = row43.createCell(36);
						String chucvu = "";
						if (PpCbcv.getValue() != null) {
							if (PpCbcv.getValue().toString().trim().contains(",")) {
								chucvu = PpCbcv.getValue().toString().trim().split(",", 2)[1].trim();
							}
						}
						AI5.setCellValue(chucvu);
						final Cell AJ5 = row43.createCell(37);
						AJ5.setCellValue(PpTenCb.getValue().toString().trim());

						final Cell AK5 = row43.createCell(38);
						AK5.setCellValue(this.diaBanVp);

						final Property PpLinhVuc = table.getContainerProperty(i, "LINH_VUC_GIAO_THONG");
						final Cell AL5 = row43.createCell(39);
						String linhVucGt = "";
						if (PpLinhVuc.getValue() != "") {
							if (Integer.parseInt(PpLinhVuc.getValue().toString().trim()) == 1) {
								linhVucGt = "Đường bộ";
							} else if (Integer.parseInt(PpLinhVuc.getValue().toString().trim()) == 2) {
								linhVucGt = "Đường sắt";
							} else if (Integer.parseInt(PpLinhVuc.getValue().toString().trim()) == 4) {
								linhVucGt = "Đường thủy";
							}
						}
						AL5.setCellValue(linhVucGt);

					}
				} else if ((this.cmbNhomHv.isEmpty() || this.cmbNhomHv.getValue() == "Tất cả") && this.cmbHvvp.isEmpty()
						&& ((this.cmbLoaiGiayTo.isEmpty() == false || this.cmbLoaiGiayTo.getValue() != "Tất cả")
								|| this.SoGiayToBCTH != "")) {
					// } else if ((this.cmbnhomHvvp.isEmpty()) &&
					// this.cmbHvvp.isEmpty()
					// && (this.cmbLoaiGiayTo.isEmpty() == false &&
					// this.cmbLoaiGiayTo.getValue() != "Tất cả")) {
					final ViewBaocaothQd01DAO dao01 = new ViewBaocaothQd01DAO();
					final List<ViewBaocaothQd01> listVuViec01;
					listVuViec01 = dao01.BaoCaoTheoTieuChiPt(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH,
							this.ToChucBCTH, this.LinhVucBCTH, this.ngheNghiepNvpBCTH, this.tuNamBCTH, this.denNamBCTH,
							this.loaiPhuongTienBCTH, this.tangVatBCTH, this.hinhThucPhatBCTH, this.TienPhatTuBCTH,
							this.TienPhatDenBCTH, this.HinhThucNopPhatBCTH, this.nhomhvBCTH, this.nhomhvKhacBCTH,
							this.hanhViVPBCTH, this.xpbsBCTH, this.tuocTuNgayBCTH, this.tuocDenNgayBCTH,
							this.chucVuBCTH, this.khobacBCTH, this.nghiDinhBCTH, this.tenNvpBCTH, this.DiaChiNvpBCTH,
							this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH,
							this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH);

					for (final ViewBaocaothQd01 vuviec01 : listVuViec01) {
						count1++;
						table.addItem(new Object[] { String.valueOf(listVuViec01.indexOf(vuviec01) + 1),
								String.valueOf(vuviec01.getMaVuViec()), String.valueOf(vuviec01.getMaRutgon()),
								String.valueOf(vuviec01.getLoaiBbQd()), String.valueOf(vuviec01.getSoBienBan()),
								String.valueOf(vuviec01.getTenNguoiNvp()), String.valueOf(vuviec01.getDiaDanhHcId()),
								vuviec01.getDiaChiNvp(), vuviec01.getNgaySinhNvpNhap(), vuviec01.getNgheNghiepNvp(),
								vuviec01.getLoaiPhuongTien(), vuviec01.getNoiDungVphc(), vuviec01.getBienKiemSoat(),
								vuviec01.getHangGplx(), vuviec01.getGplx(), vuviec01.getThoiGianVphc(),
								vuviec01.getDiaDiemVphc(), vuviec01.getTangVatTg(), vuviec01.getThoiHanTg(),
								vuviec01.getHinhThucXp(),
								vuviec01.getTongMucPhat() == null ? null : (String.valueOf(vuviec01.getTongMucPhat())),
								vuviec01.getXuPhatBoSung(),
								vuviec01.getTuNgayXpbs() == null ? "" : (String.valueOf(vuviec01.getTuNgayXpbs())),
								vuviec01.getDenNgayXpbs() == null ? "" : (String.valueOf(vuviec01.getDenNgayXpbs())),
								vuviec01.getBienPhapKhacPhuc(), vuviec01.getNgayLapBb(), vuviec01.getTenDonVi(),
								vuviec01.getTenCanBo(),
								vuviec01.getLinhVucGiaoThong() == null ? ""
										: String.valueOf(vuviec01.getLinhVucGiaoThong()),
								String.valueOf(vuviec01.getToChuc()), vuviec01.getKiemDinh(),
								vuviec01.getTrangThaiNp() == null ? "" : String.valueOf(vuviec01.getTrangThaiNp()),
								vuviec01.getHanhViVpId() == null ? "" : String.valueOf(vuviec01.getHanhViVpId()),
								vuviec01.getLuatTcId() == null ? "" : String.valueOf(vuviec01.getLuatTcId()),
								vuviec01.getTuocTuNgay() == null ? "" : (String.valueOf(vuviec01.getTuocTuNgay())),
								vuviec01.getTuocDenNgay() == null ? "" : (String.valueOf(vuviec01.getTuocDenNgay())),
								vuviec01.getCapBacChucVu(), vuviec01.getDonViThuTien(), vuviec01.getTangVatTraLai(),
								vuviec01.getTenDonViLap(), vuviec01.getLoaiTvtgId(), vuviec01.getLoaiTvtg(),
								vuviec01.getHangTvtg(), vuviec01.getNoiCapTvtg(), vuviec01.getHieuLucTvtg(),
								vuviec01.getTinhTrang(), vuviec01.getSoLuong(), vuviec01.getDonViTinh(),
								vuviec01.getSoTvtg(), vuviec01.getNoiDungHvvp(), vuviec01.getDieuLuat(),
								vuviec01.getThoiHanTu(), vuviec01.getThoiHanDen(), vuviec01.getTangVat() }, count1);

					}

					final ViewBaocaothBb43DAO dao43 = new ViewBaocaothBb43DAO();
					final List<ViewBaocaothBb43> listVuViec43;
					listVuViec43 = dao43.BaoCaoTheoTieuChiPt(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH,
							this.ToChucBCTH, this.LinhVucBCTH, this.ngheNghiepNvpBCTH, this.tuNamBCTH, this.denNamBCTH,
							this.loaiPhuongTienBCTH, this.tangVatBCTH, this.hinhThucPhatBCTH, this.TienPhatTuBCTH,
							this.TienPhatDenBCTH, this.HinhThucNopPhatBCTH, this.nhomhvBCTH, this.nhomhvKhacBCTH,
							this.hanhViVPBCTH, this.xpbsBCTH, this.tuocTuNgayBCTH, this.tuocDenNgayBCTH,
							this.chucVuBCTH, this.khobacBCTH, this.nghiDinhBCTH, this.tenNvpBCTH, this.DiaChiNvpBCTH,
							this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH,
							this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH);
					for (final ViewBaocaothBb43 vuviec43 : listVuViec43) {
						count1++;
						table.addItem(new Object[] { String.valueOf(listVuViec43.indexOf(vuviec43) + 1),
								vuviec43.getMaVuViec(), vuviec43.getMaRutgon(), String.valueOf(vuviec43.getLoaiBbQd()),
								vuviec43.getSoBienBan(), vuviec43.getTenNguoiNvp(),
								String.valueOf(vuviec43.getDiaDanhHcId()), vuviec43.getDiaChiNvp(),
								vuviec43.getNgaySinhNvpNhap(), vuviec43.getNgheNghiepNvp(),
								vuviec43.getLoaiPhuongTien(), vuviec43.getNoiDungVphc(), vuviec43.getBienKiemSoat(),
								vuviec43.getHangGplx(), vuviec43.getGplx(), vuviec43.getThoiGianVphc(),
								vuviec43.getDiaDiemVphc(), vuviec43.getTangVatTg(), vuviec43.getThoiHanTg(),
								vuviec43.getHinhThucXp(),
								vuviec43.getTongMucPhat() == null ? "" : String.valueOf(vuviec43.getTongMucPhat()),
								vuviec43.getXuPhatBoSung(), vuviec43.getTuNgayXpbs(), vuviec43.getDenNgayXpbs(),
								vuviec43.getBienPhapKhacPhuc(), vuviec43.getNgayLapBb(), vuviec43.getTenDonVi(),
								vuviec43.getTenCanBo(),
								vuviec43.getLinhVucGiaoThong() == null ? ""
										: String.valueOf(vuviec43.getLinhVucGiaoThong()),
								String.valueOf(vuviec43.getToChuc()), vuviec43.getKiemDinh(), vuviec43.getTrangThaiNp(),
								String.valueOf(vuviec43.getHanhViVpId()), String.valueOf(vuviec43.getLuatTcId()),
								vuviec43.getTuocTuNgay() == null ? "" : (String.valueOf(vuviec43.getTuocTuNgay())),
								vuviec43.getTuocDenNgay() == null ? "" : (String.valueOf(vuviec43.getTuocDenNgay())),
								vuviec43.getCapBacChucVu(), vuviec43.getDonViThuTien(), vuviec43.getTangVatTraLai(),
								vuviec43.getTenDonViLap(), String.valueOf(vuviec43.getLoaiTvtgId()),
								vuviec43.getLoaiTvtg(), vuviec43.getHangTvtg(), vuviec43.getNoiCapTvtg(),
								vuviec43.getHieuLucTvtg(), vuviec43.getTinhTrang(),
								String.valueOf(vuviec43.getSoLuong()), vuviec43.getDonViTinh(), vuviec43.getSoTvtg(),
								vuviec43.getNoiDungHvvp(), vuviec43.getDieuLuat(), vuviec43.getThoiHanTu(),
								vuviec43.getThoiHanDen(), vuviec43.getTangVat() }, count1);

					}

					final ViewBaocaothBb50DAO dao50 = new ViewBaocaothBb50DAO();
					final List<ViewBaocaothBb50> listVuViec50;
					listVuViec50 = dao50.BaoCaoTheoTieuChiPt(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH,
							this.ToChucBCTH, this.LinhVucBCTH, this.ngheNghiepNvpBCTH, this.tuNamBCTH, this.denNamBCTH,
							this.loaiPhuongTienBCTH, this.tangVatBCTH, this.hinhThucPhatBCTH, this.TienPhatTuBCTH,
							this.TienPhatDenBCTH, this.HinhThucNopPhatBCTH, this.nhomhvBCTH, this.nhomhvKhacBCTH,
							this.hanhViVPBCTH, this.xpbsBCTH, this.tuocTuNgayBCTH, this.tuocDenNgayBCTH,
							this.chucVuBCTH, this.khobacBCTH, this.nghiDinhBCTH, this.tenNvpBCTH, this.DiaChiNvpBCTH,
							this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH,
							this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH);
					for (final ViewBaocaothBb50 vuviec50 : listVuViec50) {
						count1++;
						table.addItem(
								new Object[] { String.valueOf(listVuViec50.indexOf(vuviec50) + 1),
										vuviec50.getMaVuViec(), vuviec50.getMaRutgon(),
										String.valueOf(vuviec50.getLoaiBbQd()), vuviec50.getSoBienBan(),
										vuviec50.getTenNguoiNvp(), String.valueOf(vuviec50.getDiaDanhHcId()),
										vuviec50.getDiaChiNvp(), vuviec50.getNgaySinhNvpNhap(),
										vuviec50.getNgheNghiepNvp(), vuviec50.getLoaiPhuongTien(),
										vuviec50.getNoiDungVphc(), vuviec50.getBienKiemSoat(), vuviec50.getHangGplx(),
										vuviec50.getGplx(), vuviec50.getThoiGianVphc(), vuviec50.getDiaDiemVphc(),
										vuviec50.getTangVatTg(), vuviec50.getThoiHanTg(), vuviec50.getHinhThucXp(),
										vuviec50.getTongMucPhat(), vuviec50.getXuPhatBoSung(), vuviec50.getTuNgayXpbs(),
										vuviec50.getDenNgayXpbs(), vuviec50.getBienPhapKhacPhuc(),
										vuviec50.getNgayLapBb(), vuviec50.getTenDonVi(), vuviec50.getTenCanBo(),
										vuviec50.getLinhVucGiaoThong() == null ? ""
												: String.valueOf(vuviec50.getLinhVucGiaoThong()),
										String.valueOf(vuviec50.getToChuc()), vuviec50.getKiemDinh(),
										vuviec50.getTrangThaiNp(), vuviec50.getHanhViVpId(), vuviec50.getLuatTcId(),
										vuviec50.getTuocTuNgay(), vuviec50.getTuocDenNgay(), vuviec50.getCapBacChucVu(),
										vuviec50.getDonViThuTien(), vuviec50.getTangVatTraLai(),
										vuviec50.getTenDonViLap(), String.valueOf(vuviec50.getLoaiTvtgId()),
										vuviec50.getLoaiTvtg(), vuviec50.getHangTvtg(), vuviec50.getNoiCapTvtg(),
										vuviec50.getHieuLucTvtg(), vuviec50.getTinhTrang(),
										String.valueOf(vuviec50.getSoLuong()), vuviec50.getDonViTinh(),
										vuviec50.getSoTvtg(), vuviec50.getNoiDungHvvp(), vuviec50.getDieuLuat(),
										vuviec50.getThoiHanTu(), vuviec50.getThoiHanDen(), vuviec50.getTangVat() },
								count1);

					}

					final ViewBaocaothBb60DAO dao60 = new ViewBaocaothBb60DAO();
					final List<ViewBaocaothBb60> listVuViec60;
					listVuViec60 = dao60.BaoCaoTheoTieuChiPt(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH,
							this.ToChucBCTH, this.LinhVucBCTH, this.ngheNghiepNvpBCTH, this.tuNamBCTH, this.denNamBCTH,
							this.loaiPhuongTienBCTH, this.tangVatBCTH, this.hinhThucPhatBCTH, this.TienPhatTuBCTH,
							this.TienPhatDenBCTH, this.HinhThucNopPhatBCTH, this.nhomhvBCTH, this.nhomhvKhacBCTH,
							this.hanhViVPBCTH, this.xpbsBCTH, this.tuocTuNgayBCTH, this.tuocDenNgayBCTH,
							this.chucVuBCTH, this.khobacBCTH, this.nghiDinhBCTH, this.tenNvpBCTH, this.DiaChiNvpBCTH,
							this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH,
							this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH);

					for (final ViewBaocaothBb60 vuviec60 : listVuViec60) {
						count1++;
						table.addItem(
								new Object[] { String.valueOf(listVuViec60.indexOf(vuviec60) + 1),
										vuviec60.getMaVuViec(), vuviec60.getMaRutgon(),
										String.valueOf(vuviec60.getLoaiBbQd()), vuviec60.getSoBienBan(),
										vuviec60.getTenNguoiNvp(), String.valueOf(vuviec60.getDiaDanhHcId()),
										vuviec60.getDiaChiNvp(), vuviec60.getNgaySinhNvpNhap(),
										vuviec60.getNgheNghiepNvp(), vuviec60.getLoaiPhuongTien(),
										vuviec60.getNoiDungVphc(), vuviec60.getBienKiemSoat(), vuviec60.getHangGplx(),
										vuviec60.getGplx(), vuviec60.getThoiGianVphc(), vuviec60.getDiaDiemVphc(),
										vuviec60.getTangVatTg(), vuviec60.getThoiHanTg(), vuviec60.getHinhThucXp(),
										vuviec60.getTongMucPhat(), vuviec60.getXuPhatBoSung(), vuviec60.getTuNgayXpbs(),
										vuviec60.getDenNgayXpbs(), vuviec60.getBienPhapKhacPhuc(),
										vuviec60.getNgayLapBb(), vuviec60.getTenDonVi(), vuviec60.getTenCanBo(),
										vuviec60.getLinhVucGiaoThong() == null ? ""
												: String.valueOf(vuviec60.getLinhVucGiaoThong()),
										String.valueOf(vuviec60.getToChuc()), vuviec60.getKiemDinh(),
										vuviec60.getTrangThaiNp(), vuviec60.getHanhViVpId(), vuviec60.getLuatTcId(),
										vuviec60.getTuocTuNgay(), vuviec60.getTuocDenNgay(), vuviec60.getCapBacChucVu(),
										vuviec60.getDonViThuTien(), vuviec60.getTangVatTraLai(),
										vuviec60.getTenDonViLap(), String.valueOf(vuviec60.getLoaiTvtgId()),
										vuviec60.getLoaiTvtg(), vuviec60.getHangTvtg(), vuviec60.getNoiCapTvtg(),
										vuviec60.getHieuLucTvtg(), vuviec60.getTinhTrang(),
										String.valueOf(vuviec60.getSoLuong()), vuviec60.getDonViTinh(),
										vuviec60.getSoTvtg(), vuviec60.getNoiDungHvvp(), vuviec60.getDieuLuat(),
										vuviec60.getThoiHanTu(), vuviec60.getThoiHanDen(), vuviec60.getTangVat() },
								count1);

					}

					final ViewBaocaothQd02DAO dao02 = new ViewBaocaothQd02DAO();
					final List<ViewBaocaothQd02> listVuViec02;
					listVuViec02 = dao02.BaoCaoTheoTieuChiPt(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH,
							this.ToChucBCTH, this.LinhVucBCTH, this.ngheNghiepNvpBCTH, this.tuNamBCTH, this.denNamBCTH,
							this.loaiPhuongTienBCTH, this.tangVatBCTH, this.hinhThucPhatBCTH, this.TienPhatTuBCTH,
							this.TienPhatDenBCTH, this.HinhThucNopPhatBCTH, this.nhomhvBCTH, this.nhomhvKhacBCTH,
							this.hanhViVPBCTH, this.xpbsBCTH, this.tuocTuNgayBCTH, this.tuocDenNgayBCTH,
							this.chucVuBCTH, this.khobacBCTH, this.nghiDinhBCTH, this.tenNvpBCTH, this.DiaChiNvpBCTH,
							this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH,
							this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH);
					for (final ViewBaocaothQd02 vuviec02 : listVuViec02) {
						final String stt = String.valueOf(listVuViec02.indexOf(vuviec02) + 1);
						count1++;
						table.addItem(new Object[] { stt, String.valueOf(vuviec02.getMaVuViec()),
								String.valueOf(vuviec02.getMaRutgon()), String.valueOf(vuviec02.getLoaiBbQd()),
								String.valueOf(vuviec02.getSoBienBan()), String.valueOf(vuviec02.getTenNguoiNvp()),
								String.valueOf(vuviec02.getDiaDanhHcId()), vuviec02.getDiaChiNvp(),
								vuviec02.getNgaySinhNvpNhap(), vuviec02.getNgheNghiepNvp(),
								vuviec02.getLoaiPhuongTien(), vuviec02.getNoiDungVphc(), vuviec02.getBienKiemSoat(),
								vuviec02.getHangGplx(), vuviec02.getGplx(), vuviec02.getThoiGianVphc(),
								vuviec02.getDiaDiemVphc(), vuviec02.getTangVatTg(), vuviec02.getThoiHanTg(),
								vuviec02.getHinhThucXp(),
								vuviec02.getTongMucPhat() == null ? null : (String.valueOf(vuviec02.getTongMucPhat())),
								vuviec02.getXuPhatBoSung(),
								vuviec02.getTuNgayXpbs() == null ? "" : (String.valueOf(vuviec02.getTuNgayXpbs())),
								vuviec02.getDenNgayXpbs() == null ? "" : (String.valueOf(vuviec02.getDenNgayXpbs())),
								vuviec02.getBienPhapKhacPhuc(), vuviec02.getNgayLapBb(), vuviec02.getTenDonVi(),
								vuviec02.getTenCanBo(),
								vuviec02.getLinhVucGiaoThong() == null ? ""
										: String.valueOf(vuviec02.getLinhVucGiaoThong()),
								String.valueOf(vuviec02.getToChuc()), vuviec02.getKiemDinh(),
								vuviec02.getTrangThaiNp() == null ? "" : String.valueOf(vuviec02.getTrangThaiNp()),
								vuviec02.getHanhViVpId() == null ? "" : String.valueOf(vuviec02.getHanhViVpId()),
								vuviec02.getLuatTcId() == null ? "" : String.valueOf(vuviec02.getLuatTcId()),
								vuviec02.getTuocTuNgay() == null ? "" : (String.valueOf(vuviec02.getTuocTuNgay())),
								vuviec02.getTuocDenNgay() == null ? "" : (String.valueOf(vuviec02.getTuocDenNgay())),
								vuviec02.getCapBacChucVu(), vuviec02.getDonViThuTien(), vuviec02.getTangVatTraLai(),
								vuviec02.getTenDonViLap(), vuviec02.getLoaiTvtgId(), vuviec02.getLoaiTvtg(),
								vuviec02.getHangTvtg(), vuviec02.getNoiCapTvtg(), vuviec02.getHieuLucTvtg(),
								vuviec02.getTinhTrang(), vuviec02.getSoLuong(), vuviec02.getDonViTinh(),
								vuviec02.getSoTvtg(), vuviec02.getNoiDungHvvp(), vuviec02.getDieuLuat(),
								vuviec02.getThoiHanTu(), vuviec02.getThoiHanDen(), vuviec02.getTangVat() }, count1);

					}

					final ViewBaocaothQd18DAO dao18 = new ViewBaocaothQd18DAO();
					final List<ViewBaocaothQd18> listVuViec18;
					listVuViec18 = dao18.BaoCaoTheoTieuChiPt(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH,
							this.ToChucBCTH, this.LinhVucBCTH, this.ngheNghiepNvpBCTH, this.tuNamBCTH, this.denNamBCTH,
							this.loaiPhuongTienBCTH, this.tangVatBCTH, this.hinhThucPhatBCTH, this.TienPhatTuBCTH,
							this.TienPhatDenBCTH, this.HinhThucNopPhatBCTH, this.nhomhvBCTH, this.nhomhvKhacBCTH,
							this.hanhViVPBCTH, this.xpbsBCTH, this.tuocTuNgayBCTH, this.tuocDenNgayBCTH,
							this.chucVuBCTH, this.khobacBCTH, this.nghiDinhBCTH, this.tenNvpBCTH, this.DiaChiNvpBCTH,
							this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH,
							this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH);
					for (final ViewBaocaothQd18 vuviec18 : listVuViec18) {
						count1++;
						table.addItem(new Object[] { String.valueOf(listVuViec18.indexOf(vuviec18) + 1),
								String.valueOf(vuviec18.getMaVuViec()), String.valueOf(vuviec18.getMaRutgon()),
								String.valueOf(vuviec18.getLoaiBbQd()), String.valueOf(vuviec18.getSoBienBan()),
								String.valueOf(vuviec18.getTenNguoiNvp()), String.valueOf(vuviec18.getDiaDanhHcId()),
								vuviec18.getDiaChiNvp(), vuviec18.getNgaySinhNvpNhap(), vuviec18.getNgheNghiepNvp(),
								vuviec18.getLoaiPhuongTien(), vuviec18.getNoiDungVphc(), vuviec18.getBienKiemSoat(),
								vuviec18.getHangGplx(), vuviec18.getGplx(), vuviec18.getThoiGianVphc(),
								vuviec18.getDiaDiemVphc(), vuviec18.getTangVatTg(), vuviec18.getThoiHanTg(),
								vuviec18.getHinhThucXp(), vuviec18.getTongMucPhat(), vuviec18.getXuPhatBoSung(),
								vuviec18.getTuNgayXpbs(), vuviec18.getDenNgayXpbs(), vuviec18.getBienPhapKhacPhuc(),
								vuviec18.getNgayLapBb(), vuviec18.getTenDonVi(), vuviec18.getTenCanBo(),
								vuviec18.getLinhVucGiaoThong() == null ? ""
										: String.valueOf(vuviec18.getLinhVucGiaoThong()),
								String.valueOf(vuviec18.getToChuc()), vuviec18.getKiemDinh(), vuviec18.getTrangThaiNp(),
								vuviec18.getHanhViVpId(), vuviec18.getLuatTcId(), vuviec18.getTuocTuNgay(),
								vuviec18.getTuocDenNgay(), vuviec18.getCapBacChucVu(), vuviec18.getDonViThuTien(),
								vuviec18.getTangVatTraLai(), vuviec18.getTenDonViLap(),
								String.valueOf(vuviec18.getLoaiTvtgId()), vuviec18.getLoaiTvtg(),
								vuviec18.getHangTvtg(), vuviec18.getNoiCapTvtg(), vuviec18.getHieuLucTvtg(),
								vuviec18.getTinhTrang(), String.valueOf(vuviec18.getSoLuong()), vuviec18.getDonViTinh(),
								vuviec18.getSoTvtg(), vuviec18.getNoiDungHvvp(), vuviec18.getDieuLuat(),
								vuviec18.getThoiHanTu() == null ? "" : (String.valueOf(vuviec18.getThoiHanTu())),
								vuviec18.getThoiHanDen() == null ? "" : (String.valueOf(vuviec18.getThoiHanDen())),
								vuviec18.getTangVat() }, count1);
						;

					}

					final ViewBaocaothQd20DAO dao20 = new ViewBaocaothQd20DAO();
					final List<ViewBaocaothQd20> listVuViec20;
					listVuViec20 = dao20.BaoCaoTheoTieuChiPt(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH,
							this.ToChucBCTH, this.LinhVucBCTH, this.ngheNghiepNvpBCTH, this.tuNamBCTH, this.denNamBCTH,
							this.loaiPhuongTienBCTH, this.tangVatBCTH, this.hinhThucPhatBCTH, this.TienPhatTuBCTH,
							this.TienPhatDenBCTH, this.HinhThucNopPhatBCTH, this.nhomhvBCTH, this.nhomhvKhacBCTH,
							this.hanhViVPBCTH, this.xpbsBCTH, this.tuocTuNgayBCTH, this.tuocDenNgayBCTH,
							this.chucVuBCTH, this.khobacBCTH, this.nghiDinhBCTH, this.tenNvpBCTH, this.DiaChiNvpBCTH,
							this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH,
							this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH);
					for (final ViewBaocaothQd20 vuviec20 : listVuViec20) {
						count1++;
						table.addItem(new Object[] { String.valueOf(listVuViec20.indexOf(vuviec20) + 1),
								String.valueOf(vuviec20.getMaVuViec()), String.valueOf(vuviec20.getMaRutgon()),
								String.valueOf(vuviec20.getLoaiBbQd()), String.valueOf(vuviec20.getSoBienBan()),
								String.valueOf(vuviec20.getTenNguoiNvp()), String.valueOf(vuviec20.getDiaDanhHcId()),
								vuviec20.getDiaChiNvp(), vuviec20.getNgaySinhNvpNhap(), vuviec20.getNgheNghiepNvp(),
								vuviec20.getLoaiPhuongTien(), vuviec20.getNoiDungVphc(), vuviec20.getBienKiemSoat(),
								vuviec20.getHangGplx(), vuviec20.getGplx(), vuviec20.getThoiGianVphc(),
								vuviec20.getDiaDiemVphc(), vuviec20.getTangVatTg(), vuviec20.getThoiHanTg(),
								vuviec20.getHinhThucXp(), vuviec20.getTongMucPhat(), vuviec20.getXuPhatBoSung(),
								vuviec20.getTuNgayXpbs(), vuviec20.getDenNgayXpbs(), vuviec20.getBienPhapKhacPhuc(),
								vuviec20.getNgayLapBb(), vuviec20.getTenDonVi(), vuviec20.getTenCanBo(),
								vuviec20.getLinhVucGiaoThong() == null ? ""
										: String.valueOf(vuviec20.getLinhVucGiaoThong()),
								String.valueOf(vuviec20.getToChuc()), vuviec20.getKiemDinh(), vuviec20.getTrangThaiNp(),
								vuviec20.getHanhViVpId(), vuviec20.getLuatTcId(), vuviec20.getTuocTuNgay(),
								vuviec20.getTuocDenNgay(), vuviec20.getCapBacChucVu(), vuviec20.getDonViThuTien(),
								vuviec20.getTangVatTraLai(), vuviec20.getTenDonViLap(),
								String.valueOf(vuviec20.getLoaiTvtgId()), vuviec20.getLoaiTvtg(),
								vuviec20.getHangTvtg(), vuviec20.getNoiCapTvtg(), vuviec20.getHieuLucTvtg(),
								vuviec20.getTinhTrang(), String.valueOf(vuviec20.getSoLuong()), vuviec20.getDonViTinh(),
								vuviec20.getSoTvtg(), vuviec20.getNoiDungHvvp(), vuviec20.getDieuLuat(),
								vuviec20.getThoiHanTu(), vuviec20.getThoiHanDen(), vuviec20.getTangVat() }, count1);

					}

					// final ViewBaocaothQd01DAO dao01 = new
					// ViewBaocaothQd01DAO();
					// final List<ViewBaocaothQd01> listVuViec01;
					// listVuViec01 =
					// dao01.BaoCaoTheoTieuChiPt(this.danhSachBCTH,
					// this.fromdateBCTH, this.todateBCTH,
					// this.ToChucBCTH, this.LinhVucBCTH,
					// this.ngheNghiepNvpBCTH, this.tuNamBCTH, this.denNamBCTH,
					// this.loaiPhuongTienBCTH, this.tangVatBCTH,
					// this.hinhThucPhatBCTH, this.TienPhatTuBCTH,
					// this.TienPhatDenBCTH, this.HinhThucNopPhatBCTH,
					// this.nhomhvBCTH, this.nhomhvKhacBCTH,
					// this.hanhViVPBCTH, this.xpbsBCTH, this.tuocTuNgayBCTH,
					// this.tuocDenNgayBCTH,
					// this.chucVuBCTH, this.khobacBCTH, this.nghiDinhBCTH,
					// this.tenNvpBCTH, this.DiaChiNvpBCTH,
					// this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH,
					// this.BKSBCTH, this.soBBBCTH,
					// this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH,
					// this.thoiHanDenBCTH,
					// this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH,
					// this.XaBCTH, this.QuocLoBCTH,
					// this.TuyenDuongBCTH);
					for (final Object i : table.getItemIds()) {

						final Property propertyMaVV = table.getContainerProperty(i, "MA_RUTGON");
						final Row row43 = sheet.createRow(rowNum++);
						final Cell A5 = row43.createCell(0);
						A5.setCellValue(row43.getRowNum() - 2);
						final Cell B5 = row43.createCell(1);
						B5.setCellValue((String) propertyMaVV.getValue());

						final Property propertyLOAI_BB_QD = table.getContainerProperty(i, "LOAI_BB_QD");
						final long loaiBB = Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim());

						final Cell C5 = row43.createCell(2);
						if (loaiBB == 43) {
							C5.setCellValue("Biên bản vi phạm hành chính");
						} else if (loaiBB == 2) {
							C5.setCellValue("Quyết định xử phạt vi phạm hành chính");
						} else if (loaiBB == 1) {
							C5.setCellValue("Quyết định xử phạt vi phạm hành chính không lập biên bản");
						} else if (loaiBB == 18) {
							C5.setCellValue("Quyết định tạm giữ tang vật phương tiện");
						} else if (loaiBB == 20) {
							C5.setCellValue("Quyết định trả lại tang vật phương tiện");
						} else if (loaiBB == 60) {
							C5.setCellValue("Biên bản trả lại tang vật phương tiện");
						} else if (loaiBB == 50) {
							C5.setCellValue("Biên bản tạm giữ tang vật phương tiện");
						}

						final Property propertySoBB = table.getContainerProperty(i, "SO_BIEN_BAN");
						final Cell D5 = row43.createCell(3);
						D5.setCellValue(propertySoBB.getValue().toString());

						final Property propertyTC = table.getContainerProperty(i, "TO_CHUC");
						final Cell E5 = row43.createCell(4);
						if (propertyTC.getValue() != null) {
							if (Long.parseLong(propertyTC.getValue().toString().trim()) == 0) {
								E5.setCellValue("Cá nhân");
							} else if (Long.parseLong(propertyTC.getValue().toString().trim()) == 1) {
								E5.setCellValue("Tổ chức");
							}
						} else {
							E5.setCellValue("");
						}

						final Property propertyTen = table.getContainerProperty(i, "TEN_NGUOI_NVP");
						final Cell F5 = row43.createCell(5);
						F5.setCellValue((String) propertyTen.getValue());
						final Property propertyThoiGianVphc = table.getContainerProperty(i, "THOI_GIAN_VPHC");
						final Cell G5 = row43.createCell(6);
						G5.setCellValue((String) propertyThoiGianVphc.getValue());

						final Property propertyDiaChi = table.getContainerProperty(i, "DIA_CHI_NVP");
						final Cell H5 = row43.createCell(7);
						H5.setCellValue((String) propertyDiaChi.getValue());

						final Cell I5 = row43.createCell(8);
						I5.setCellValue("");

						final Property propertyNgaySinh = table.getContainerProperty(i, "NGAY_SINH_NVP_NHAP");
						final Cell J5 = row43.createCell(9);
						J5.setCellValue((String) propertyNgaySinh.getValue());

						final Cell K5 = row43.createCell(10);
						K5.setCellValue("");

						final Property propertyNgheNghiep = table.getContainerProperty(i, "NGHE_NGHIEP_NVP");
						final Cell L5 = row43.createCell(11);
						L5.setCellValue((String) propertyNgheNghiep.getValue());

						final Property propertylpt = table.getContainerProperty(i, "LOAI_PHUONG_TIEN");
						final Cell M5 = row43.createCell(12);
						M5.setCellValue((String) propertylpt.getValue());

						final Property propertyBKS = table.getContainerProperty(i, "BIEN_KIEM_SOAT");
						final Cell N5 = row43.createCell(13);
						N5.setCellValue((String) propertyBKS.getValue());

						final Property propertydiaDiemVPHC = table.getContainerProperty(i, "DIA_DIEM_VPHC");
						final Cell O5 = row43.createCell(14);
						O5.setCellValue((String) propertydiaDiemVPHC.getValue());

						final Property propertyNoiDungVphc = table.getContainerProperty(i, "NOI_DUNG_VPHC");
						final Cell P5 = row43.createCell(15);
						String NoiDung = "";
						final int count = 0;
						if (propertyNoiDungVphc.getValue() == null) {
							NoiDung = "";
						} else {
							NoiDung = propertyNoiDungVphc.getValue().toString().trim();
							if (NoiDung.contains("quy định tại") == true) {
								P5.setCellValue(substrFromNghiD(typefile(NoiDung)));
							} else {
								P5.setCellValue(NoiDung);
							}
						}
						String sbHvvp = "";
						final Cell Q5 = row43.createCell(16);
						String NdHvpp = "";
						if (propertyNoiDungVphc.getValue() != null) {
							NdHvpp = (String) propertyNoiDungVphc.getValue();
						}
						if (loaiBB != 2 && loaiBB != 1) {
							if (NdHvpp.contains("quy định tại") == true) {
								sbHvvp = substrHvvp(typefile(NdHvpp));
							} else {
								sbHvvp = NdHvpp;
							}
						} else {
							sbHvvp = "";
						}

						final Property propertyDieuLuat = table.getContainerProperty(i, "DIEU_LUAT");
						Q5.setCellValue((String) propertyDieuLuat.getValue());

						final Property propertyTvTg = table.getContainerProperty(i, "TANG_VAT_TG");
						final Cell R5 = row43.createCell(17);
						R5.setCellValue(tenNhom);

						String tangVat = "";
						if (propertyTvTg.getValue() == null) {
							tangVat = "";
						} else {
							tangVat = (String) propertyTvTg.getValue();
							if (tangVat.contains("nơi cấp") == true) {
								substr(tangVat);
							} else {
								tangVat = "";
							}
						}

						final Property ppHang = table.getContainerProperty(i, "HANG_TVTG");
						final Property ppHieuLuc = table.getContainerProperty(i, "HIEU_LUC_TVTG");
						final Property ppTinhTrang = table.getContainerProperty(i, "TINH_TRANG");
						final Property ppSoLuong = table.getContainerProperty(i, "SO_LUONG");
						final Property ppDonViTinh = table.getContainerProperty(i, "DON_VI_TINH");
						final Property ppNoiCap = table.getContainerProperty(i, "NOI_CAP_TVTG");
						final Property ppLoaiTvtg = table.getContainerProperty(i, "LOAI_TVTG");
						final Cell S5 = row43.createCell(18);
						String tangvat = "";
						if (this.cmbLoaiGiayTo.getValue() != "" && this.cmbLoaiGiayTo.getValue() != null) {
							if (this.cmbLoaiGiayTo.getValue() != "Tất cả") {
								if (this.cmbLoaiGiayTo.getValue() == "GPLX") {
									tangvat = "01 giấy phép lái xe hạng "
											+ (ppHang.getValue() == null ? ""
													: (ppHang.getValue().toString().trim()) + " ")
											+ (ppTinhTrang.getValue() == null ? ""
													: (ppTinhTrang.getValue().toString().trim()) + " ")
											+ (ppHieuLuc.getValue() == null ? ""
													: (ppHieuLuc.getValue() == "" ? ""
															: (" giá trị đến "
																	+ ppHieuLuc.getValue().toString().trim())))
											+ " nơi cấp " + ppNoiCap.getValue().toString().trim() + "; ";
								} else if (this.cmbLoaiGiayTo.getValue() == "Đăng ký") {
									tangvat = "01 giấy đăng ký xe:" + " "
											+ (ppTinhTrang.getValue() == null ? ""
													: (ppTinhTrang.getValue().toString().trim()))
											+ " nơi cấp" + ppNoiCap.getValue() + "; ";
								} else if (this.cmbLoaiGiayTo.getValue() == "Kiểm định") {
									tangvat = "01 giấy chứng nhận kiểm định an toàn kỹ thuật và bảo vệ môi trường:"
											+ " "
											+ ppTinhTrang.getValue().toString()
													.trim()
											+ (ppHieuLuc
													.getValue() == null
															? ""
															: (ppHieuLuc.getValue() == "" ? ""
																	: (" có giá trị đến "
																			+ ppHieuLuc.getValue().toString().trim())))
											+ "; ";
								} else if (this.cmbLoaiGiayTo.getValue() == "Phương tiện") {
									tangvat = "01 Phương tiện" + " " + (ppTinhTrang.getValue() == null ? ""
											: (ppTinhTrang.getValue().toString().trim())) + "; ";
								} else if (this.cmbLoaiGiayTo.getValue() == "Khác") {
									final String Khac = (ppSoLuong.getValue() == null ? ""
											: (Long.parseLong(ppSoLuong.getValue().toString()) == 0 ? ""
													: (ppSoLuong.getValue().toString().trim()) + " "))
											+ (ppDonViTinh.getValue() == null ? ""
													: (ppDonViTinh.getValue().toString().trim()) + " ")
											+ (ppLoaiTvtg.getValue() == "" ? ""
													: (ppLoaiTvtg.getValue().toString().trim()) + " ")
											+ (ppTinhTrang.getValue() == null ? ""
													: (ppTinhTrang.getValue().toString().trim()));
									tangvat = Khac.trim() + ";";
								}
							} else {
								tangvat = (String) propertyTvTg.getValue();
							}
						} else {
							tangvat = (String) propertyTvTg.getValue();
						}
						S5.setCellValue(tangvat);
						final Cell T5 = row43.createCell(19);

						if (ppHang.getValue() != null) {
							if (ppHang.getValue().toString().contains("N/A")) {
								T5.setCellValue("");
							} else {
								T5.setCellValue((String) ppHang.getValue());
							}
						} else {
							T5.setCellValue("");
						}

						final Cell U5 = row43.createCell(20);
						String gplx = "";
						if ((this.cmbLoaiGiayTo.getValue() != "" && this.cmbLoaiGiayTo.getValue() != null)) {
							if (this.cmbLoaiGiayTo.getValue() != "Tất cả") {
								if (this.cmbLoaiGiayTo.getValue() == "GPLX") {
									gplx = (ppTinhTrang.getValue() == null ? ""
											: (ppTinhTrang.getValue().toString().trim()));
								} else if (this.cmbLoaiGiayTo.getValue() == "Đăng ký") {
									gplx = (ppTinhTrang.getValue() == null ? ""
											: (ppTinhTrang.getValue().toString().trim()));
								} else if (ppTinhTrang.getValue() == "Kiểm định") {
									gplx = (ppTinhTrang.getValue() == null ? ""
											: (ppTinhTrang.getValue().toString().trim()));
									// } else if (this.cmbLoaiGiayTo.getValue()
									// ==
									// "Phương tiện") {
									// gplx = "01 Phương tiện" + " "
									// + (vuviec43.getTinhTrang() == null ? "" :
									// (vuviec43.getTinhTrang().trim()))
									// + "; ";
								} else if (this.cmbLoaiGiayTo.getValue() == "Khác") {
									final String Khac = (ppSoLuong.getValue() == null ? ""
											: (Long.parseLong(ppSoLuong.getValue().toString()) == 0 ? ""
													: (ppSoLuong.getValue().toString().trim()) + " "))
											+ (ppDonViTinh.getValue() == null ? ""
													: (ppDonViTinh.getValue().toString().trim()) + " ")
											+ (ppLoaiTvtg.getValue() == "" ? ""
													: (ppLoaiTvtg.getValue().toString().trim()) + " ")
											+ (ppTinhTrang.getValue() == null ? ""
													: (ppTinhTrang.getValue().toString().trim()));
									gplx = Khac.trim() + ";";
								}

							} else {
								if (ppTinhTrang.getValue() != null) {
									if (ppTinhTrang.getValue().toString().contains("N/A")) {
										gplx = "";
									} else if (ppTinhTrang.getValue().toString().contains("BKS")) {
										gplx = "";
									} else {
										gplx = ppTinhTrang.getValue().toString();
									}
								}
							}
						} else {
							if (ppTinhTrang.getValue() != null) {
								if (ppTinhTrang.getValue().toString().contains("N/A")) {
									gplx = "";
								} else if (ppTinhTrang.getValue().toString().contains("BKS")) {
									gplx = "";
								} else {
									gplx = ppTinhTrang.getValue().toString();
								}
							}
						}
						if (this.SoGiayToBCTH != "") {
							gplx = "Số :" + this.SoGiayToBCTH;
						}
						U5.setCellValue(gplx);
						final Cell V5 = row43.createCell(21);
						String Hieuluc = "";
						// if ((this.cmbLoaiGiayTo.getValue() != "" &&
						// this.cmbLoaiGiayTo.getValue() != null)) {
						if (ppHieuLuc.getValue() != null) {
							if (ppHieuLuc.getValue().toString().contains("N/A")) {
								Hieuluc = "";
							} else {
								Hieuluc = ppHieuLuc.getValue().toString();
							}

						}
						// }
						V5.setCellValue(Hieuluc);
						final Cell W5 = row43.createCell(22);
						// W5.setCellValue(substr(tangVat));
						if (ppNoiCap.getValue() != null) {
							if (ppNoiCap.getValue().toString().contains("N/A")) {
								W5.setCellValue("");
							} else {
								W5.setCellValue((String) ppNoiCap.getValue());
							}
						}

						final Property propertyThoiHanTg = table.getContainerProperty(i, "THOI_HAN_TG");
						final Cell X5 = row43.createCell(23);
						X5.setCellValue((String) propertyThoiHanTg.getValue());

						final Property propertyTrHTXP = table.getContainerProperty(i, "HINH_THUC_XP");
						final Cell Y5 = row43.createCell(24);
						Y5.setCellValue((String) propertyTrHTXP.getValue());

						final Property propertyTienNp = table.getContainerProperty(i, "TONG_MUC_PHAT");
						final Cell Z5 = row43.createCell(25);
						final NumberFormat fmMoney = NumberFormat.getCurrencyInstance(new Locale("vn", "VN"));
						final DecimalFormatSymbols formatSym = ((DecimalFormat) fmMoney).getDecimalFormatSymbols();
						formatSym.setCurrencySymbol("");
						((DecimalFormat) fmMoney).setDecimalFormatSymbols(formatSym);
						if (propertyTienNp.getValue() != null && propertyTienNp.getValue() != "") {
							Z5.setCellValue(fmMoney.format(Long.parseLong((String) propertyTienNp.getValue()))
									.replace(",", "."));
						}

						final Property propertyTrThaiNp = table.getContainerProperty(i, "TRANG_THAI_NP");
						final Cell AA5 = row43.createCell(26);
						String trangThai = "";
						if (propertyTrThaiNp.getValue() != null && propertyTrThaiNp.getValue() != "") {
							if (Integer.parseInt(propertyTrThaiNp.getValue().toString().trim()) == 3) {
								trangThai = "Đã thanh toán qua DVC";
							} else if (Integer.parseInt(propertyTrThaiNp.getValue().toString().trim()) == 4) {
								trangThai = "Đã gửi SMS";
							} else if (Integer.parseInt(propertyTrThaiNp.getValue().toString().trim()) == 5) {
								trangThai = "Đã thanh toán trực tiếp";
							}
						}
						AA5.setCellValue(trangThai);

						final SimpleDateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");
						final Property propertyXpbs = table.getContainerProperty(i, "XU_PHAT_BO_SUNG");
						final Cell AB5 = row43.createCell(27);
						AB5.setCellValue((String) propertyXpbs.getValue());

						final Property PptuNgayXpbs = table.getContainerProperty(i, "TU_NGAY_XPBS");
						final Cell AC5 = row43.createCell(28);
						String FromDateXpbs = "";
						if (PptuNgayXpbs.getValue() != null && PptuNgayXpbs.getValue() != "") {
							FromDateXpbs = (String) PptuNgayXpbs.getValue();
							final DateFormat formatDateXpbs = new SimpleDateFormat("yyyy-MM-dd");
							Date dateXpbs;
							try {
								dateXpbs = formatDateXpbs.parse(FromDateXpbs);
								AC5.setCellValue(dfm.format(dateXpbs));
							} catch (final ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						final Property PpDenNgayXpbs = table.getContainerProperty(i, "DEN_NGAY_XPBS");
						final Cell AD5 = row43.createCell(29);
						String ToDateXpbs = "";
						if (PpDenNgayXpbs.getValue() != null && PpDenNgayXpbs.getValue() != "") {
							ToDateXpbs = (String) PpDenNgayXpbs.getValue();
							final DateFormat formatDateXpbs = new SimpleDateFormat("yyyy-MM-dd");
							Date dateXpbs;
							try {
								dateXpbs = formatDateXpbs.parse(ToDateXpbs);
								AD5.setCellValue(dfm.format(dateXpbs));
							} catch (final ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						final Property PpBPKP = table.getContainerProperty(i, "BIEN_PHAP_KHAC_PHUC");
						final Cell AE5 = row43.createCell(30);
						AE5.setCellValue((String) PpBPKP.getValue());

						final Property PpTvtl = table.getContainerProperty(i, "TANG_VAT_TRA_LAI");
						final Cell AT5 = row43.createCell(31);
						AT5.setCellValue((String) PpTvtl.getValue());

						final Property PpNgayLapBB = table.getContainerProperty(i, "NGAY_LAP_BB");
						final Cell AF5 = row43.createCell(32);
						Date newDate;
						if (PpNgayLapBB.getValue() != null) {
							newDate = (Date) PpNgayLapBB.getValue();
							AF5.setCellValue(dfm.format(newDate));
						}

						final Property PpTenDvLap = table.getContainerProperty(i, "TEN_DON_VI_LAP");
						final Cell AM5 = row43.createCell(33);
						AM5.setCellValue((String) PpTenDvLap.getValue());

						final Property PpTenDv = table.getContainerProperty(i, "TEN_DON_VI");
						final Cell AG5 = row43.createCell(34);
						AG5.setCellValue((String) PpTenDv.getValue());

						final Property PpTenCb = table.getContainerProperty(i, "TEN_CAN_BO");
						final Cell AH5 = row43.createCell(35);
						AH5.setCellValue((String) PpTenCb.getValue());

						final Property PpCbcv = table.getContainerProperty(i, "CAP_BAC_CHUC_VU");
						final Cell AI5 = row43.createCell(36);
						String chucvu = "";
						if (PpCbcv.getValue() != null) {
							if (PpCbcv.getValue().toString().trim().contains(",")) {
								chucvu = PpCbcv.getValue().toString().trim().split(",", 2)[1].trim();
							}
						}
						AI5.setCellValue(chucvu);

						final Cell AJ5 = row43.createCell(37);
						AJ5.setCellValue(PpTenCb.getValue().toString().trim());

						final Cell AK5 = row43.createCell(38);
						AK5.setCellValue(this.diaBanVp);

						final Property PpLinhVuc = table.getContainerProperty(i, "LINH_VUC_GIAO_THONG");
						;
						final Cell AL5 = row43.createCell(39);
						String linhVucGt = "";
						if (PpLinhVuc.getValue() != "") {
							if (Integer.parseInt(PpLinhVuc.getValue().toString().trim()) == 1) {
								linhVucGt = "Đường bộ";
							} else if (Integer.parseInt(PpLinhVuc.getValue().toString().trim()) == 2) {
								linhVucGt = "Đường sắt";
							} else if (Integer.parseInt(PpLinhVuc.getValue().toString().trim()) == 4) {
								linhVucGt = "Đường thủy";
							}
						}
						AL5.setCellValue(linhVucGt);
					}

				} else if (((this.cmbNhomHv.isEmpty() == false || this.cmbNhomHv.getValue() != "Tất cả")
						|| this.cmbHvvp.isEmpty() == false)
						&& ((this.cmbLoaiGiayTo.isEmpty() == true || this.cmbLoaiGiayTo.getValue() == "Tất cả")
								&& this.SoGiayToBCTH == "")) {

					final ViewBaocaothQd01DAO dao01 = new ViewBaocaothQd01DAO();
					final List<ViewBaocaothQd01> listVuViec01;
					listVuViec01 = dao01.BaoCaoTheoTieuChiLuatVaHvvp(this.danhSachBCTH, this.fromdateBCTH,
							this.todateBCTH, toChuc, linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt,
							loaiGiayTo, hinhThucXp, fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH,
							this.nhomhvKhacBCTH, this.hanhViVPBCTH, xpbs, tuocFrom, tuocTo, chucVu, khoBac,
							this.nghiDinhBCTH, this.tenNvpBCTH, this.DiaChiNvpBCTH, this.NoiCapTvBCTH,
							this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH, this.CanBoBCTH,
							this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH, this.TrangThaiXuLyBCTH,
							this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH, this.TuyenDuongBCTH,
							this.checkHvvp);

					for (final ViewBaocaothQd01 vuviec01 : listVuViec01) {
						count1++;
						table.addItem(new Object[] { String.valueOf(listVuViec01.indexOf(vuviec01) + 1),
								String.valueOf(vuviec01.getMaVuViec()), String.valueOf(vuviec01.getMaRutgon()),
								String.valueOf(vuviec01.getLoaiBbQd()), String.valueOf(vuviec01.getSoBienBan()),
								String.valueOf(vuviec01.getTenNguoiNvp()), String.valueOf(vuviec01.getDiaDanhHcId()),
								vuviec01.getDiaChiNvp(), vuviec01.getNgaySinhNvpNhap(), vuviec01.getNgheNghiepNvp(),
								vuviec01.getLoaiPhuongTien(), vuviec01.getNoiDungVphc(), vuviec01.getBienKiemSoat(),
								vuviec01.getHangGplx(), vuviec01.getGplx(), vuviec01.getThoiGianVphc(),
								vuviec01.getDiaDiemVphc(), vuviec01.getTangVatTg(), vuviec01.getThoiHanTg(),
								vuviec01.getHinhThucXp(),
								vuviec01.getTongMucPhat() == null ? null : (String.valueOf(vuviec01.getTongMucPhat())),
								vuviec01.getXuPhatBoSung(),
								vuviec01.getTuNgayXpbs() == null ? "" : (String.valueOf(vuviec01.getTuNgayXpbs())),
								vuviec01.getDenNgayXpbs() == null ? "" : (String.valueOf(vuviec01.getDenNgayXpbs())),
								vuviec01.getBienPhapKhacPhuc(), vuviec01.getNgayLapBb(), vuviec01.getTenDonVi(),
								vuviec01.getTenCanBo(),
								vuviec01.getLinhVucGiaoThong() == null ? ""
										: String.valueOf(vuviec01.getLinhVucGiaoThong()),
								String.valueOf(vuviec01.getToChuc()), vuviec01.getKiemDinh(),
								vuviec01.getTrangThaiNp() == null ? "" : String.valueOf(vuviec01.getTrangThaiNp()),
								vuviec01.getHanhViVpId() == null ? "" : String.valueOf(vuviec01.getHanhViVpId()),
								vuviec01.getLuatTcId() == null ? "" : String.valueOf(vuviec01.getLuatTcId()),
								vuviec01.getTuocTuNgay() == null ? "" : (String.valueOf(vuviec01.getTuocTuNgay())),
								vuviec01.getTuocDenNgay() == null ? "" : (String.valueOf(vuviec01.getTuocDenNgay())),
								vuviec01.getCapBacChucVu(), vuviec01.getDonViThuTien(), vuviec01.getTangVatTraLai(),
								vuviec01.getTenDonViLap(), vuviec01.getLoaiTvtgId(), vuviec01.getLoaiTvtg(),
								vuviec01.getHangTvtg(), vuviec01.getNoiCapTvtg(), vuviec01.getHieuLucTvtg(),
								vuviec01.getTinhTrang(), vuviec01.getSoLuong(), vuviec01.getDonViTinh(),
								vuviec01.getSoTvtg(), vuviec01.getNoiDungHvvp(), vuviec01.getDieuLuat(),
								vuviec01.getThoiHanTu(), vuviec01.getThoiHanDen(), vuviec01.getTangVat() }, count1);

					}

					final ViewBaocaothBb43DAO dao43 = new ViewBaocaothBb43DAO();
					final List<ViewBaocaothBb43> listVuViec43;
					listVuViec43 = dao43.BaoCaoTheoTieuChiLuatVaHvvp(this.danhSachBCTH, this.fromdateBCTH,
							this.todateBCTH, toChuc, linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt,
							loaiGiayTo, hinhThucXp, fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH,
							this.nhomhvKhacBCTH, this.hanhViVPBCTH, xpbs, tuocFrom, tuocTo, chucVu, khoBac,
							this.nghiDinhBCTH, this.tenNvpBCTH, this.DiaChiNvpBCTH, this.NoiCapTvBCTH,
							this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH, this.CanBoBCTH,
							this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH, this.TrangThaiXuLyBCTH,
							this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH, this.TuyenDuongBCTH,
							this.checkHvvp);
					for (final ViewBaocaothBb43 vuviec43 : listVuViec43) {
						count1++;
						table.addItem(new Object[] { String.valueOf(listVuViec43.indexOf(vuviec43) + 1),
								vuviec43.getMaVuViec(), vuviec43.getMaRutgon(), String.valueOf(vuviec43.getLoaiBbQd()),
								vuviec43.getSoBienBan(), vuviec43.getTenNguoiNvp(),
								String.valueOf(vuviec43.getDiaDanhHcId()), vuviec43.getDiaChiNvp(),
								vuviec43.getNgaySinhNvpNhap(), vuviec43.getNgheNghiepNvp(),
								vuviec43.getLoaiPhuongTien(), vuviec43.getNoiDungVphc(), vuviec43.getBienKiemSoat(),
								vuviec43.getHangGplx(), vuviec43.getGplx(), vuviec43.getThoiGianVphc(),
								vuviec43.getDiaDiemVphc(), vuviec43.getTangVatTg(), vuviec43.getThoiHanTg(),
								vuviec43.getHinhThucXp(),
								vuviec43.getTongMucPhat() == null ? "" : String.valueOf(vuviec43.getTongMucPhat()),
								vuviec43.getXuPhatBoSung(), vuviec43.getTuNgayXpbs(), vuviec43.getDenNgayXpbs(),
								vuviec43.getBienPhapKhacPhuc(), vuviec43.getNgayLapBb(), vuviec43.getTenDonVi(),
								vuviec43.getTenCanBo(),
								vuviec43.getLinhVucGiaoThong() == null ? ""
										: String.valueOf(vuviec43.getLinhVucGiaoThong()),
								String.valueOf(vuviec43.getToChuc()), vuviec43.getKiemDinh(), vuviec43.getTrangThaiNp(),
								String.valueOf(vuviec43.getHanhViVpId()), String.valueOf(vuviec43.getLuatTcId()),
								vuviec43.getTuocTuNgay() == null ? "" : (String.valueOf(vuviec43.getTuocTuNgay())),
								vuviec43.getTuocDenNgay() == null ? "" : (String.valueOf(vuviec43.getTuocDenNgay())),
								vuviec43.getCapBacChucVu(), vuviec43.getDonViThuTien(), vuviec43.getTangVatTraLai(),
								vuviec43.getTenDonViLap(), String.valueOf(vuviec43.getLoaiTvtgId()),
								vuviec43.getLoaiTvtg(), vuviec43.getHangTvtg(), vuviec43.getNoiCapTvtg(),
								vuviec43.getHieuLucTvtg(), vuviec43.getTinhTrang(),
								String.valueOf(vuviec43.getSoLuong()), vuviec43.getDonViTinh(), vuviec43.getSoTvtg(),
								vuviec43.getNoiDungHvvp(), vuviec43.getDieuLuat(), vuviec43.getThoiHanTu(),
								vuviec43.getThoiHanDen(), vuviec43.getTangVat() }, count1);

					}

					final ViewBaocaothBb50DAO dao50 = new ViewBaocaothBb50DAO();
					final List<ViewBaocaothBb50> listVuViec50;
					listVuViec50 = dao50.BaoCaoTheoTieuChiLuatVaHvvp(this.danhSachBCTH, this.fromdateBCTH,
							this.todateBCTH, toChuc, linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt,
							loaiGiayTo, hinhThucXp, fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH,
							this.nhomhvKhacBCTH, this.hanhViVPBCTH, xpbs, tuocFrom, tuocTo, chucVu, khoBac,
							this.nghiDinhBCTH, this.tenNvpBCTH, this.DiaChiNvpBCTH, this.NoiCapTvBCTH,
							this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH, this.CanBoBCTH,
							this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH, this.TrangThaiXuLyBCTH,
							this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH, this.TuyenDuongBCTH,
							this.checkHvvp);
					for (final ViewBaocaothBb50 vuviec50 : listVuViec50) {
						count1++;
						table.addItem(
								new Object[] { String.valueOf(listVuViec50.indexOf(vuviec50) + 1),
										vuviec50.getMaVuViec(), vuviec50.getMaRutgon(),
										String.valueOf(vuviec50.getLoaiBbQd()), vuviec50.getSoBienBan(),
										vuviec50.getTenNguoiNvp(), String.valueOf(vuviec50.getDiaDanhHcId()),
										vuviec50.getDiaChiNvp(), vuviec50.getNgaySinhNvpNhap(),
										vuviec50.getNgheNghiepNvp(), vuviec50.getLoaiPhuongTien(),
										vuviec50.getNoiDungVphc(), vuviec50.getBienKiemSoat(), vuviec50.getHangGplx(),
										vuviec50.getGplx(), vuviec50.getThoiGianVphc(), vuviec50.getDiaDiemVphc(),
										vuviec50.getTangVatTg(), vuviec50.getThoiHanTg(), vuviec50.getHinhThucXp(),
										vuviec50.getTongMucPhat(), vuviec50.getXuPhatBoSung(), vuviec50.getTuNgayXpbs(),
										vuviec50.getDenNgayXpbs(), vuviec50.getBienPhapKhacPhuc(),
										vuviec50.getNgayLapBb(), vuviec50.getTenDonVi(), vuviec50.getTenCanBo(),
										vuviec50.getLinhVucGiaoThong() == null ? ""
												: String.valueOf(vuviec50.getLinhVucGiaoThong()),
										String.valueOf(vuviec50.getToChuc()), vuviec50.getKiemDinh(),
										vuviec50.getTrangThaiNp(), vuviec50.getHanhViVpId(), vuviec50.getLuatTcId(),
										vuviec50.getTuocTuNgay(), vuviec50.getTuocDenNgay(), vuviec50.getCapBacChucVu(),
										vuviec50.getDonViThuTien(), vuviec50.getTangVatTraLai(),
										vuviec50.getTenDonViLap(), String.valueOf(vuviec50.getLoaiTvtgId()),
										vuviec50.getLoaiTvtg(), vuviec50.getHangTvtg(), vuviec50.getNoiCapTvtg(),
										vuviec50.getHieuLucTvtg(), vuviec50.getTinhTrang(),
										String.valueOf(vuviec50.getSoLuong()), vuviec50.getDonViTinh(),
										vuviec50.getSoTvtg(), vuviec50.getNoiDungHvvp(), vuviec50.getDieuLuat(),
										vuviec50.getThoiHanTu(), vuviec50.getThoiHanDen(), vuviec50.getTangVat() },
								count1);

					}

					final ViewBaocaothBb60DAO dao60 = new ViewBaocaothBb60DAO();
					final List<ViewBaocaothBb60> listVuViec60;
					listVuViec60 = dao60.BaoCaoTheoTieuChiLuatVaHvvp(this.danhSachBCTH, this.fromdateBCTH,
							this.todateBCTH, toChuc, linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt,
							loaiGiayTo, hinhThucXp, fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH,
							this.nhomhvKhacBCTH, this.hanhViVPBCTH, xpbs, tuocFrom, tuocTo, chucVu, khoBac,
							this.nghiDinhBCTH, this.tenNvpBCTH, this.DiaChiNvpBCTH, this.NoiCapTvBCTH,
							this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH, this.CanBoBCTH,
							this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH, this.TrangThaiXuLyBCTH,
							this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH, this.TuyenDuongBCTH,
							this.checkHvvp);

					for (final ViewBaocaothBb60 vuviec60 : listVuViec60) {
						count1++;
						table.addItem(
								new Object[] { String.valueOf(listVuViec60.indexOf(vuviec60) + 1),
										vuviec60.getMaVuViec(), vuviec60.getMaRutgon(),
										String.valueOf(vuviec60.getLoaiBbQd()), vuviec60.getSoBienBan(),
										vuviec60.getTenNguoiNvp(), String.valueOf(vuviec60.getDiaDanhHcId()),
										vuviec60.getDiaChiNvp(), vuviec60.getNgaySinhNvpNhap(),
										vuviec60.getNgheNghiepNvp(), vuviec60.getLoaiPhuongTien(),
										vuviec60.getNoiDungVphc(), vuviec60.getBienKiemSoat(), vuviec60.getHangGplx(),
										vuviec60.getGplx(), vuviec60.getThoiGianVphc(), vuviec60.getDiaDiemVphc(),
										vuviec60.getTangVatTg(), vuviec60.getThoiHanTg(), vuviec60.getHinhThucXp(),
										vuviec60.getTongMucPhat(), vuviec60.getXuPhatBoSung(), vuviec60.getTuNgayXpbs(),
										vuviec60.getDenNgayXpbs(), vuviec60.getBienPhapKhacPhuc(),
										vuviec60.getNgayLapBb(), vuviec60.getTenDonVi(), vuviec60.getTenCanBo(),
										vuviec60.getLinhVucGiaoThong() == null ? ""
												: String.valueOf(vuviec60.getLinhVucGiaoThong()),
										String.valueOf(vuviec60.getToChuc()), vuviec60.getKiemDinh(),
										vuviec60.getTrangThaiNp(), vuviec60.getHanhViVpId(), vuviec60.getLuatTcId(),
										vuviec60.getTuocTuNgay(), vuviec60.getTuocDenNgay(), vuviec60.getCapBacChucVu(),
										vuviec60.getDonViThuTien(), vuviec60.getTangVatTraLai(),
										vuviec60.getTenDonViLap(), String.valueOf(vuviec60.getLoaiTvtgId()),
										vuviec60.getLoaiTvtg(), vuviec60.getHangTvtg(), vuviec60.getNoiCapTvtg(),
										vuviec60.getHieuLucTvtg(), vuviec60.getTinhTrang(),
										String.valueOf(vuviec60.getSoLuong()), vuviec60.getDonViTinh(),
										vuviec60.getSoTvtg(), vuviec60.getNoiDungHvvp(), vuviec60.getDieuLuat(),
										vuviec60.getThoiHanTu(), vuviec60.getThoiHanDen(), vuviec60.getTangVat() },
								count1);

					}

					final ViewBaocaothQd02DAO dao02 = new ViewBaocaothQd02DAO();
					final List<ViewBaocaothQd02> listVuViec02;
					listVuViec02 = dao02.BaoCaoTheoTieuChiLuatVaHvvp(this.danhSachBCTH, this.fromdateBCTH,
							this.todateBCTH, toChuc, linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt,
							loaiGiayTo, hinhThucXp, fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH,
							this.nhomhvKhacBCTH, this.hanhViVPBCTH, xpbs, tuocFrom, tuocTo, chucVu, khoBac,
							this.nghiDinhBCTH, this.tenNvpBCTH, this.DiaChiNvpBCTH, this.NoiCapTvBCTH,
							this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH, this.CanBoBCTH,
							this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH, this.TrangThaiXuLyBCTH,
							this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH, this.TuyenDuongBCTH,
							this.checkHvvp);
					for (final ViewBaocaothQd02 vuviec02 : listVuViec02) {
						final String stt = String.valueOf(listVuViec02.indexOf(vuviec02) + 1);
						count1++;
						table.addItem(new Object[] { stt, String.valueOf(vuviec02.getMaVuViec()),
								String.valueOf(vuviec02.getMaRutgon()), String.valueOf(vuviec02.getLoaiBbQd()),
								String.valueOf(vuviec02.getSoBienBan()), String.valueOf(vuviec02.getTenNguoiNvp()),
								String.valueOf(vuviec02.getDiaDanhHcId()), vuviec02.getDiaChiNvp(),
								vuviec02.getNgaySinhNvpNhap(), vuviec02.getNgheNghiepNvp(),
								vuviec02.getLoaiPhuongTien(), vuviec02.getNoiDungVphc(), vuviec02.getBienKiemSoat(),
								vuviec02.getHangGplx(), vuviec02.getGplx(), vuviec02.getThoiGianVphc(),
								vuviec02.getDiaDiemVphc(), vuviec02.getTangVatTg(), vuviec02.getThoiHanTg(),
								vuviec02.getHinhThucXp(),
								vuviec02.getTongMucPhat() == null ? null : (String.valueOf(vuviec02.getTongMucPhat())),
								vuviec02.getXuPhatBoSung(),
								vuviec02.getTuNgayXpbs() == null ? "" : (String.valueOf(vuviec02.getTuNgayXpbs())),
								vuviec02.getDenNgayXpbs() == null ? "" : (String.valueOf(vuviec02.getDenNgayXpbs())),
								vuviec02.getBienPhapKhacPhuc(), vuviec02.getNgayLapBb(), vuviec02.getTenDonVi(),
								vuviec02.getTenCanBo(),
								vuviec02.getLinhVucGiaoThong() == null ? ""
										: String.valueOf(vuviec02.getLinhVucGiaoThong()),
								String.valueOf(vuviec02.getToChuc()), vuviec02.getKiemDinh(),
								vuviec02.getTrangThaiNp() == null ? "" : String.valueOf(vuviec02.getTrangThaiNp()),
								vuviec02.getHanhViVpId() == null ? "" : String.valueOf(vuviec02.getHanhViVpId()),
								vuviec02.getLuatTcId() == null ? "" : String.valueOf(vuviec02.getLuatTcId()),
								vuviec02.getTuocTuNgay() == null ? "" : (String.valueOf(vuviec02.getTuocTuNgay())),
								vuviec02.getTuocDenNgay() == null ? "" : (String.valueOf(vuviec02.getTuocDenNgay())),
								vuviec02.getCapBacChucVu(), vuviec02.getDonViThuTien(), vuviec02.getTangVatTraLai(),
								vuviec02.getTenDonViLap(), vuviec02.getLoaiTvtgId(), vuviec02.getLoaiTvtg(),
								vuviec02.getHangTvtg(), vuviec02.getNoiCapTvtg(), vuviec02.getHieuLucTvtg(),
								vuviec02.getTinhTrang(), vuviec02.getSoLuong(), vuviec02.getDonViTinh(),
								vuviec02.getSoTvtg(), vuviec02.getNoiDungHvvp(), vuviec02.getDieuLuat(),
								vuviec02.getThoiHanTu(), vuviec02.getThoiHanDen(), vuviec02.getTangVat() }, count1);

					}

					final ViewBaocaothQd18DAO dao18 = new ViewBaocaothQd18DAO();
					final List<ViewBaocaothQd18> listVuViec18;
					listVuViec18 = dao18.BaoCaoTheoTieuChiLuatVaHvvp(this.danhSachBCTH, this.fromdateBCTH,
							this.todateBCTH, toChuc, linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt,
							loaiGiayTo, hinhThucXp, fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH,
							this.nhomhvKhacBCTH, this.hanhViVPBCTH, xpbs, tuocFrom, tuocTo, chucVu, khoBac,
							this.nghiDinhBCTH, this.tenNvpBCTH, this.DiaChiNvpBCTH, this.NoiCapTvBCTH,
							this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH, this.CanBoBCTH,
							this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH, this.TrangThaiXuLyBCTH,
							this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH, this.TuyenDuongBCTH,
							this.checkHvvp);
					for (final ViewBaocaothQd18 vuviec18 : listVuViec18) {
						count1++;
						table.addItem(new Object[] { String.valueOf(listVuViec18.indexOf(vuviec18) + 1),
								String.valueOf(vuviec18.getMaVuViec()), String.valueOf(vuviec18.getMaRutgon()),
								String.valueOf(vuviec18.getLoaiBbQd()), String.valueOf(vuviec18.getSoBienBan()),
								String.valueOf(vuviec18.getTenNguoiNvp()), String.valueOf(vuviec18.getDiaDanhHcId()),
								vuviec18.getDiaChiNvp(), vuviec18.getNgaySinhNvpNhap(), vuviec18.getNgheNghiepNvp(),
								vuviec18.getLoaiPhuongTien(), vuviec18.getNoiDungVphc(), vuviec18.getBienKiemSoat(),
								vuviec18.getHangGplx(), vuviec18.getGplx(), vuviec18.getThoiGianVphc(),
								vuviec18.getDiaDiemVphc(), vuviec18.getTangVatTg(), vuviec18.getThoiHanTg(),
								vuviec18.getHinhThucXp(), vuviec18.getTongMucPhat(), vuviec18.getXuPhatBoSung(),
								vuviec18.getTuNgayXpbs(), vuviec18.getDenNgayXpbs(), vuviec18.getBienPhapKhacPhuc(),
								vuviec18.getNgayLapBb(), vuviec18.getTenDonVi(), vuviec18.getTenCanBo(),
								vuviec18.getLinhVucGiaoThong() == null ? ""
										: String.valueOf(vuviec18.getLinhVucGiaoThong()),
								String.valueOf(vuviec18.getToChuc()), vuviec18.getKiemDinh(), vuviec18.getTrangThaiNp(),
								vuviec18.getHanhViVpId(), vuviec18.getLuatTcId(), vuviec18.getTuocTuNgay(),
								vuviec18.getTuocDenNgay(), vuviec18.getCapBacChucVu(), vuviec18.getDonViThuTien(),
								vuviec18.getTangVatTraLai(), vuviec18.getTenDonViLap(),
								String.valueOf(vuviec18.getLoaiTvtgId()), vuviec18.getLoaiTvtg(),
								vuviec18.getHangTvtg(), vuviec18.getNoiCapTvtg(), vuviec18.getHieuLucTvtg(),
								vuviec18.getTinhTrang(), String.valueOf(vuviec18.getSoLuong()), vuviec18.getDonViTinh(),
								vuviec18.getSoTvtg(), vuviec18.getNoiDungHvvp(), vuviec18.getDieuLuat(),
								vuviec18.getThoiHanTu() == null ? "" : (String.valueOf(vuviec18.getThoiHanTu())),
								vuviec18.getThoiHanDen() == null ? "" : (String.valueOf(vuviec18.getThoiHanDen())),
								vuviec18.getTangVat() }, count1);
						;

					}

					final ViewBaocaothQd20DAO dao20 = new ViewBaocaothQd20DAO();
					final List<ViewBaocaothQd20> listVuViec20;
					listVuViec20 = dao20.BaoCaoTheoTieuChiLuatVaHvvp(this.danhSachBCTH, this.fromdateBCTH,
							this.todateBCTH, toChuc, linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt,
							loaiGiayTo, hinhThucXp, fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH,
							this.nhomhvKhacBCTH, this.hanhViVPBCTH, xpbs, tuocFrom, tuocTo, chucVu, khoBac,
							this.nghiDinhBCTH, this.tenNvpBCTH, this.DiaChiNvpBCTH, this.NoiCapTvBCTH,
							this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH, this.soBBBCTH, this.CanBoBCTH,
							this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH, this.TrangThaiXuLyBCTH,
							this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH, this.TuyenDuongBCTH,
							this.checkHvvp);
					for (final ViewBaocaothQd20 vuviec20 : listVuViec20) {
						count1++;
						table.addItem(new Object[] { String.valueOf(listVuViec20.indexOf(vuviec20) + 1),
								String.valueOf(vuviec20.getMaVuViec()), String.valueOf(vuviec20.getMaRutgon()),
								String.valueOf(vuviec20.getLoaiBbQd()), String.valueOf(vuviec20.getSoBienBan()),
								String.valueOf(vuviec20.getTenNguoiNvp()), String.valueOf(vuviec20.getDiaDanhHcId()),
								vuviec20.getDiaChiNvp(), vuviec20.getNgaySinhNvpNhap(), vuviec20.getNgheNghiepNvp(),
								vuviec20.getLoaiPhuongTien(), vuviec20.getNoiDungVphc(), vuviec20.getBienKiemSoat(),
								vuviec20.getHangGplx(), vuviec20.getGplx(), vuviec20.getThoiGianVphc(),
								vuviec20.getDiaDiemVphc(), vuviec20.getTangVatTg(), vuviec20.getThoiHanTg(),
								vuviec20.getHinhThucXp(), vuviec20.getTongMucPhat(), vuviec20.getXuPhatBoSung(),
								vuviec20.getTuNgayXpbs(), vuviec20.getDenNgayXpbs(), vuviec20.getBienPhapKhacPhuc(),
								vuviec20.getNgayLapBb(), vuviec20.getTenDonVi(), vuviec20.getTenCanBo(),
								vuviec20.getLinhVucGiaoThong() == null ? ""
										: String.valueOf(vuviec20.getLinhVucGiaoThong()),
								String.valueOf(vuviec20.getToChuc()), vuviec20.getKiemDinh(), vuviec20.getTrangThaiNp(),
								vuviec20.getHanhViVpId(), vuviec20.getLuatTcId(), vuviec20.getTuocTuNgay(),
								vuviec20.getTuocDenNgay(), vuviec20.getCapBacChucVu(), vuviec20.getDonViThuTien(),
								vuviec20.getTangVatTraLai(), vuviec20.getTenDonViLap(),
								String.valueOf(vuviec20.getLoaiTvtgId()), vuviec20.getLoaiTvtg(),
								vuviec20.getHangTvtg(), vuviec20.getNoiCapTvtg(), vuviec20.getHieuLucTvtg(),
								vuviec20.getTinhTrang(), String.valueOf(vuviec20.getSoLuong()), vuviec20.getDonViTinh(),
								vuviec20.getSoTvtg(), vuviec20.getNoiDungHvvp(), vuviec20.getDieuLuat(),
								vuviec20.getThoiHanTu(), vuviec20.getThoiHanDen(), vuviec20.getTangVat() }, count1);

					}

					// final ViewBaocaothQd01DAO dao01 = new
					// ViewBaocaothQd01DAO();
					// final List<ViewBaocaothQd01> listVuViec01;
					// listVuViec01 =
					// dao01.BaoCaoTheoTieuChiLuatVaHvvp(this.danhSachBCTH,
					// this.fromdateBCTH,
					// this.todateBCTH, toChuc, linhVuc, ngheNghiep,
					// FromYearOfBirth, ToYearOfBirth, lpt,
					// loaiGiayTo, hinhThucXp, fromMoney, toMoney, hinhThucNp,
					// this.nhomhvBCTH,
					// this.nhomhvKhacBCTH, this.hanhViVPBCTH, xpbs, tuocFrom,
					// tuocTo, chucVu, khoBac,
					// this.nghiDinhBCTH, this.tenNvpBCTH, this.DiaChiNvpBCTH,
					// this.NoiCapTvBCTH,
					// this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH,
					// this.soBBBCTH, this.CanBoBCTH,
					// this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
					// this.TrangThaiXuLyBCTH,
					// this.TinhBCTH, this.QuanBCTH, this.XaBCTH,
					// this.QuocLoBCTH, this.TuyenDuongBCTH,
					// this.checkHvvp);

					for (final Object i : table.getItemIds()) {

						final Property propertyMaVV = table.getContainerProperty(i, "MA_RUTGON");
						final Row row43 = sheet.createRow(rowNum++);
						final Cell A5 = row43.createCell(0);
						A5.setCellValue(row43.getRowNum() - 2);
						final Cell B5 = row43.createCell(1);
						B5.setCellValue((String) propertyMaVV.getValue());

						final Property propertyLOAI_BB_QD = table.getContainerProperty(i, "LOAI_BB_QD");
						final long loaiBB = Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim());

						final Cell C5 = row43.createCell(2);
						if (loaiBB == 43) {
							C5.setCellValue("Biên bản vi phạm hành chính");
						} else if (loaiBB == 2) {
							C5.setCellValue("Quyết định xử phạt vi phạm hành chính");
						} else if (loaiBB == 1) {
							C5.setCellValue("Quyết định xử phạt vi phạm hành chính không lập biên bản");
						} else if (loaiBB == 18) {
							C5.setCellValue("Quyết định tạm giữ tang vật phương tiện");
						} else if (loaiBB == 20) {
							C5.setCellValue("Quyết định trả lại tang vật phương tiện");
						} else if (loaiBB == 60) {
							C5.setCellValue("Biên bản trả lại tang vật phương tiện");
						} else if (loaiBB == 50) {
							C5.setCellValue("Biên bản tạm giữ tang vật phương tiện");
						}

						final Property propertySoBB = table.getContainerProperty(i, "SO_BIEN_BAN");
						final Cell D5 = row43.createCell(3);
						D5.setCellValue(propertySoBB.getValue().toString());

						final Property propertyTC = table.getContainerProperty(i, "TO_CHUC");
						final Cell E5 = row43.createCell(4);
						if (propertyTC.getValue() != null) {
							if (Long.parseLong(propertyTC.getValue().toString().trim()) == 0) {
								E5.setCellValue("Cá nhân");
							} else if (Long.parseLong(propertyTC.getValue().toString().trim()) == 1) {
								E5.setCellValue("Tổ chức");
							}
						} else {
							E5.setCellValue("");
						}

						final Property propertyTen = table.getContainerProperty(i, "TEN_NGUOI_NVP");
						final Cell F5 = row43.createCell(5);
						F5.setCellValue((String) propertyTen.getValue());
						final Property propertyThoiGianVphc = table.getContainerProperty(i, "THOI_GIAN_VPHC");
						final Cell G5 = row43.createCell(6);
						G5.setCellValue((String) propertyThoiGianVphc.getValue());

						final Property propertyDiaChi = table.getContainerProperty(i, "DIA_CHI_NVP");
						final Cell H5 = row43.createCell(7);
						H5.setCellValue((String) propertyDiaChi.getValue());

						final Cell I5 = row43.createCell(8);
						I5.setCellValue("");

						final Property propertyNgaySinh = table.getContainerProperty(i, "NGAY_SINH_NVP_NHAP");
						final Cell J5 = row43.createCell(9);
						J5.setCellValue((String) propertyNgaySinh.getValue());

						final Cell K5 = row43.createCell(10);
						K5.setCellValue("");

						final Property propertyNgheNghiep = table.getContainerProperty(i, "NGHE_NGHIEP_NVP");
						final Cell L5 = row43.createCell(11);
						L5.setCellValue((String) propertyNgheNghiep.getValue());

						final Property propertylpt = table.getContainerProperty(i, "LOAI_PHUONG_TIEN");
						final Cell M5 = row43.createCell(12);
						M5.setCellValue((String) propertylpt.getValue());

						final Property propertyBKS = table.getContainerProperty(i, "BIEN_KIEM_SOAT");
						final Cell N5 = row43.createCell(13);
						N5.setCellValue((String) propertyBKS.getValue());

						final Property propertydiaDiemVPHC = table.getContainerProperty(i, "DIA_DIEM_VPHC");
						final Cell O5 = row43.createCell(14);
						O5.setCellValue((String) propertydiaDiemVPHC.getValue());

						final Property propertyNoiDungVphc = table.getContainerProperty(i, "NOI_DUNG_VPHC");
						final Cell P5 = row43.createCell(15);
						String NoiDung = "";
						if (propertyNoiDungVphc.getValue() != null) {
							NoiDung = (String) propertyNoiDungVphc.getValue();
						}
						P5.setCellValue(NoiDung);

						String sbHvvp = "";
						final Cell Q5 = row43.createCell(16);
						String NdHvpp = "";
						if (propertyNoiDungVphc.getValue() != null) {
							NdHvpp = (String) propertyNoiDungVphc.getValue();
						}
						if (loaiBB != 2 && loaiBB != 1) {
							if (NdHvpp.contains("quy định tại") == true) {
								sbHvvp = substrHvvp(typefile(NdHvpp));
							} else {
								sbHvvp = NdHvpp;
							}
						} else {
							sbHvvp = "";
						}
						final Property propertyDieuLuat = table.getContainerProperty(i, "DIEU_LUAT");
						Q5.setCellValue((String) propertyDieuLuat.getValue());

						final Cell R5 = row43.createCell(17);
						R5.setCellValue(tenNhom);

						final Property propertyTvTg = table.getContainerProperty(i, "TANG_VAT_TG");
						String tangVat = "";
						if (propertyTvTg.getValue() == null) {
							tangVat = "";
						} else {
							tangVat = (String) propertyTvTg.getValue();
						}
						final Cell S5 = row43.createCell(18);
						S5.setCellValue((String) propertyTvTg.getValue());

						final Property ppHang = table.getContainerProperty(i, "HANG_GPLX");
						final Cell T5 = row43.createCell(19);
						T5.setCellValue((String) ppHang.getValue());

						final Property ppGPLX = table.getContainerProperty(i, "GPLX");
						final Property ppKD = table.getContainerProperty(i, "KIEM_DINH");
						final Cell U5 = row43.createCell(20);
						String gplx = "";
						String kiemdinh = "";
						if (ppGPLX.getValue() != null) {
							gplx = "Số GPLX: " + (String) ppGPLX.getValue();
						}
						if (ppKD.getValue() != null) {
							kiemdinh = "Số giấy kiểm định: " + (String) ppKD.getValue();
						}
						final String giayto = (ppGPLX.getValue() == null ? "" : (gplx + "; "))
								+ (ppKD.getValue() == null ? "" : (kiemdinh + "; "));
						U5.setCellValue(giayto.trim());
						final Cell V5 = row43.createCell(21);
						String finalValueGiatri = "";
						if (propertyTvTg.getValue() != null) {
							final String tv = (String) propertyTvTg.getValue();
							if (tv.contains("giá trị đến") && (!tv.contains("có giá trị đến"))) {
								final int Dem = tv.toString().split("giá trị đến").length;
								final String giatriDen = tv.toString().split("giá trị đến", Dem)[1].trim();
								if (giatriDen.contains(";")) {
									int countSplit = giatriDen.split(";").length;
									if (countSplit == 1) {
										countSplit = 2;
									}
									final String giatri = giatriDen.split(";", countSplit)[0].trim();
									if (giatri.length() <= 10) {
										for (int j = giatri.length() - 1; j >= 0; j--) {
											if (giatri.charAt(j) != ';') {
												finalValueGiatri = giatri.charAt(j) + finalValueGiatri;
											} else {
												break;
											}
										}
									} else if (giatri.length() > 10) {
										finalValueGiatri = giatriDen.substring(0, 10).trim();
									}
								}
							} else if (tv.contains("có giá trị đến")) {
								final int Dem = tv.split("có giá trị đến").length;
								final String giatriDen = tv.split("có giá trị đến", Dem)[1].trim();
								String GiaTriWithoutDau = "";
								if (giatriDen.contains(":")) {
									int count2Dots = giatriDen.split(":").length;
									if (count2Dots == 1) {
										count2Dots = 2;
									}
									GiaTriWithoutDau = giatriDen.split(":", count2Dots)[1].trim();
								} else if (!giatriDen.contains(":")) {
									GiaTriWithoutDau = giatriDen;
								}
								if (GiaTriWithoutDau.contains(";")) {
									final int countSplit = GiaTriWithoutDau.split(";").length;
									if (countSplit <= 1) {
										finalValueGiatri = GiaTriWithoutDau;
									} else if (countSplit > 1) {
										final String giatri = GiaTriWithoutDau.split(";", countSplit)[0];
										if (giatri.length() <= 10) {
											for (int j = giatri.length() - 1; j >= 0; j--) {
												if (giatri.charAt(j) != ';') {
													finalValueGiatri = giatri.charAt(j) + finalValueGiatri;
												} else {
													break;
												}
											}
										} else if (giatri.length() > 10) {
											finalValueGiatri = giatri.substring(0, 10).trim();
										}
									}
								}
							}
						}
						V5.setCellValue(finalValueGiatri);

						final Cell W5 = row43.createCell(22);
						W5.setCellValue(substr(tangVat));

						final Property propertyThoiHanTg = table.getContainerProperty(i, "THOI_HAN_TG");
						final Cell X5 = row43.createCell(23);
						X5.setCellValue((String) propertyThoiHanTg.getValue());

						final Property propertyTrHTXP = table.getContainerProperty(i, "HINH_THUC_XP");
						final Cell Y5 = row43.createCell(24);
						Y5.setCellValue((String) propertyTrHTXP.getValue());

						final Property propertyTienNp = table.getContainerProperty(i, "TONG_MUC_PHAT");
						final Cell Z5 = row43.createCell(25);
						final NumberFormat fmMoney = NumberFormat.getCurrencyInstance(new Locale("vn", "VN"));
						final DecimalFormatSymbols formatSym = ((DecimalFormat) fmMoney).getDecimalFormatSymbols();
						formatSym.setCurrencySymbol("");
						((DecimalFormat) fmMoney).setDecimalFormatSymbols(formatSym);
						if (propertyTienNp.getValue() != null && propertyTienNp.getValue() != "") {
							Z5.setCellValue(fmMoney.format(Long.parseLong((String) propertyTienNp.getValue()))
									.replace(",", "."));
						}

						final Property propertyTrThaiNp = table.getContainerProperty(i, "TRANG_THAI_NP");
						final Cell AA5 = row43.createCell(26);
						String trangThai = "";
						if (propertyTrThaiNp.getValue() != null && propertyTrThaiNp.getValue() != "") {
							if (Integer.parseInt(propertyTrThaiNp.getValue().toString().trim()) == 3) {
								trangThai = "Đã thanh toán qua DVC";
							} else if (Integer.parseInt(propertyTrThaiNp.getValue().toString().trim()) == 4) {
								trangThai = "Đã gửi SMS";
							} else if (Integer.parseInt(propertyTrThaiNp.getValue().toString().trim()) == 5) {
								trangThai = "Đã thanh toán trực tiếp";
							}
						}
						AA5.setCellValue(trangThai);

						final SimpleDateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");
						final Property propertyXpbs = table.getContainerProperty(i, "XU_PHAT_BO_SUNG");
						final Cell AB5 = row43.createCell(27);
						AB5.setCellValue((String) propertyXpbs.getValue());

						final Property PptuNgayXpbs = table.getContainerProperty(i, "TU_NGAY_XPBS");
						final Cell AC5 = row43.createCell(28);
						String FromDateXpbs = "";
						if (PptuNgayXpbs.getValue() != null && PptuNgayXpbs.getValue() != "") {
							FromDateXpbs = (String) PptuNgayXpbs.getValue();
							final DateFormat formatDateXpbs = new SimpleDateFormat("yyyy-MM-dd");
							Date dateXpbs;
							try {
								dateXpbs = formatDateXpbs.parse(FromDateXpbs);
								AC5.setCellValue(dfm.format(dateXpbs));
							} catch (final ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						final Property PpDenNgayXpbs = table.getContainerProperty(i, "DEN_NGAY_XPBS");
						final Cell AD5 = row43.createCell(29);
						String ToDateXpbs = "";
						if (PpDenNgayXpbs.getValue() != null && PpDenNgayXpbs.getValue() != "") {
							ToDateXpbs = (String) PpDenNgayXpbs.getValue();
							final DateFormat formatDateXpbs = new SimpleDateFormat("yyyy-MM-dd");
							Date dateXpbs;
							try {
								dateXpbs = formatDateXpbs.parse(ToDateXpbs);
								AD5.setCellValue(dfm.format(dateXpbs));
							} catch (final ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						final Property PpBPKP = table.getContainerProperty(i, "BIEN_PHAP_KHAC_PHUC");
						final Cell AE5 = row43.createCell(30);
						AE5.setCellValue((String) PpBPKP.getValue());

						final Property PpTvtl = table.getContainerProperty(i, "TANG_VAT_TRA_LAI");
						final Cell AT5 = row43.createCell(31);
						AT5.setCellValue((String) PpTvtl.getValue());

						final Property PpNgayLapBB = table.getContainerProperty(i, "NGAY_LAP_BB");
						final Cell AF5 = row43.createCell(32);
						Date newDate;
						if (PpNgayLapBB.getValue() != null) {
							newDate = (Date) PpNgayLapBB.getValue();
							AF5.setCellValue(dfm.format(newDate));
						}

						final Property PpTenDvLap = table.getContainerProperty(i, "TEN_DON_VI_LAP");
						final Cell AM5 = row43.createCell(33);
						AM5.setCellValue((String) PpTenDvLap.getValue());

						final Property PpTenDv = table.getContainerProperty(i, "TEN_DON_VI");
						final Cell AG5 = row43.createCell(34);
						AG5.setCellValue((String) PpTenDv.getValue());

						final Property PpTenCb = table.getContainerProperty(i, "TEN_CAN_BO");
						final Cell AH5 = row43.createCell(35);
						AH5.setCellValue((String) PpTenCb.getValue());

						final Property PpCbcv = table.getContainerProperty(i, "CAP_BAC_CHUC_VU");
						final Cell AI5 = row43.createCell(36);
						String chucvu = "";
						if (PpCbcv.getValue() != null) {
							if (PpCbcv.getValue().toString().trim().contains(",")) {
								chucvu = PpCbcv.getValue().toString().trim().split(",", 2)[1].trim();
							}
						}
						AI5.setCellValue(chucvu);

						final Cell AJ5 = row43.createCell(37);
						AJ5.setCellValue(PpTenCb.getValue().toString().trim());

						final Cell AK5 = row43.createCell(38);
						AK5.setCellValue(this.diaBanVp);

						final Property PpLinhVuc = table.getContainerProperty(i, "LINH_VUC_GIAO_THONG");
						final Cell AL5 = row43.createCell(39);
						String linhVucGt = "";
						if (PpLinhVuc.getValue() != "") {
							if (Integer.parseInt(PpLinhVuc.getValue().toString().trim()) == 1) {
								linhVucGt = "Đường bộ";
							} else if (Integer.parseInt(PpLinhVuc.getValue().toString().trim()) == 2) {
								linhVucGt = "Đường sắt";
							} else if (Integer.parseInt(PpLinhVuc.getValue().toString().trim()) == 4) {
								linhVucGt = "Đường thủy";
							}
						}
						AL5.setCellValue(linhVucGt);
					}

				} else if (((this.cmbNhomHv.isEmpty() == false || this.cmbNhomHv.getValue() != "Tất cả")
						|| this.cmbHvvp.isEmpty() == false)
						&& ((this.cmbLoaiGiayTo.isEmpty() == false || this.cmbLoaiGiayTo.getValue() != "Tất cả")
								|| this.SoGiayToBCTH != "")) {

					final ViewBaocaothQd01DAO dao01 = new ViewBaocaothQd01DAO();
					final List<ViewBaocaothQd01> listVuViec01;
					listVuViec01 = dao01.BcHvvpVaPt(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH, toChuc,
							linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt, this.tangVatBCTH, hinhThucXp,
							fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH, this.nhomhvKhacBCTH, this.hanhViVPBCTH,
							xpbs, tuocFrom, tuocTo, chucVu, khoBac, this.nghiDinhBCTH, this.tenNvpBCTH,
							this.DiaChiNvpBCTH, this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH,
							this.soBBBCTH, this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH, this.checkHvvp);

					for (final ViewBaocaothQd01 vuviec01 : listVuViec01) {
						count1++;
						table.addItem(new Object[] { String.valueOf(listVuViec01.indexOf(vuviec01) + 1),
								String.valueOf(vuviec01.getMaVuViec()), String.valueOf(vuviec01.getMaRutgon()),
								String.valueOf(vuviec01.getLoaiBbQd()), String.valueOf(vuviec01.getSoBienBan()),
								String.valueOf(vuviec01.getTenNguoiNvp()), String.valueOf(vuviec01.getDiaDanhHcId()),
								vuviec01.getDiaChiNvp(), vuviec01.getNgaySinhNvpNhap(), vuviec01.getNgheNghiepNvp(),
								vuviec01.getLoaiPhuongTien(), vuviec01.getNoiDungVphc(), vuviec01.getBienKiemSoat(),
								vuviec01.getHangGplx(), vuviec01.getGplx(), vuviec01.getThoiGianVphc(),
								vuviec01.getDiaDiemVphc(), vuviec01.getTangVatTg(), vuviec01.getThoiHanTg(),
								vuviec01.getHinhThucXp(),
								vuviec01.getTongMucPhat() == null ? null : (String.valueOf(vuviec01.getTongMucPhat())),
								vuviec01.getXuPhatBoSung(),
								vuviec01.getTuNgayXpbs() == null ? "" : (String.valueOf(vuviec01.getTuNgayXpbs())),
								vuviec01.getDenNgayXpbs() == null ? "" : (String.valueOf(vuviec01.getDenNgayXpbs())),
								vuviec01.getBienPhapKhacPhuc(), vuviec01.getNgayLapBb(), vuviec01.getTenDonVi(),
								vuviec01.getTenCanBo(),
								vuviec01.getLinhVucGiaoThong() == null ? ""
										: String.valueOf(vuviec01.getLinhVucGiaoThong()),
								String.valueOf(vuviec01.getToChuc()), vuviec01.getKiemDinh(),
								vuviec01.getTrangThaiNp() == null ? "" : String.valueOf(vuviec01.getTrangThaiNp()),
								vuviec01.getHanhViVpId() == null ? "" : String.valueOf(vuviec01.getHanhViVpId()),
								vuviec01.getLuatTcId() == null ? "" : String.valueOf(vuviec01.getLuatTcId()),
								vuviec01.getTuocTuNgay() == null ? "" : (String.valueOf(vuviec01.getTuocTuNgay())),
								vuviec01.getTuocDenNgay() == null ? "" : (String.valueOf(vuviec01.getTuocDenNgay())),
								vuviec01.getCapBacChucVu(), vuviec01.getDonViThuTien(), vuviec01.getTangVatTraLai(),
								vuviec01.getTenDonViLap(), vuviec01.getLoaiTvtgId(), vuviec01.getLoaiTvtg(),
								vuviec01.getHangTvtg(), vuviec01.getNoiCapTvtg(), vuviec01.getHieuLucTvtg(),
								vuviec01.getTinhTrang(), vuviec01.getSoLuong(), vuviec01.getDonViTinh(),
								vuviec01.getSoTvtg(), vuviec01.getNoiDungHvvp(), vuviec01.getDieuLuat(),
								vuviec01.getThoiHanTu(), vuviec01.getThoiHanDen(), vuviec01.getTangVat() }, count1);

					}

					final ViewBaocaothBb43DAO dao43 = new ViewBaocaothBb43DAO();
					final List<ViewBaocaothBb43> listVuViec43;
					listVuViec43 = dao43.BcHvvpVaPt(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH, toChuc,
							linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt, this.tangVatBCTH, hinhThucXp,
							fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH, this.nhomhvKhacBCTH, this.hanhViVPBCTH,
							xpbs, tuocFrom, tuocTo, chucVu, khoBac, this.nghiDinhBCTH, this.tenNvpBCTH,
							this.DiaChiNvpBCTH, this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH,
							this.soBBBCTH, this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH, this.checkHvvp);
					for (final ViewBaocaothBb43 vuviec43 : listVuViec43) {
						count1++;
						table.addItem(new Object[] { String.valueOf(listVuViec43.indexOf(vuviec43) + 1),
								vuviec43.getMaVuViec(), vuviec43.getMaRutgon(), String.valueOf(vuviec43.getLoaiBbQd()),
								vuviec43.getSoBienBan(), vuviec43.getTenNguoiNvp(),
								String.valueOf(vuviec43.getDiaDanhHcId()), vuviec43.getDiaChiNvp(),
								vuviec43.getNgaySinhNvpNhap(), vuviec43.getNgheNghiepNvp(),
								vuviec43.getLoaiPhuongTien(), vuviec43.getNoiDungVphc(), vuviec43.getBienKiemSoat(),
								vuviec43.getHangGplx(), vuviec43.getGplx(), vuviec43.getThoiGianVphc(),
								vuviec43.getDiaDiemVphc(), vuviec43.getTangVatTg(), vuviec43.getThoiHanTg(),
								vuviec43.getHinhThucXp(),
								vuviec43.getTongMucPhat() == null ? "" : String.valueOf(vuviec43.getTongMucPhat()),
								vuviec43.getXuPhatBoSung(), vuviec43.getTuNgayXpbs(), vuviec43.getDenNgayXpbs(),
								vuviec43.getBienPhapKhacPhuc(), vuviec43.getNgayLapBb(), vuviec43.getTenDonVi(),
								vuviec43.getTenCanBo(),
								vuviec43.getLinhVucGiaoThong() == null ? ""
										: String.valueOf(vuviec43.getLinhVucGiaoThong()),
								String.valueOf(vuviec43.getToChuc()), vuviec43.getKiemDinh(), vuviec43.getTrangThaiNp(),
								String.valueOf(vuviec43.getHanhViVpId()), String.valueOf(vuviec43.getLuatTcId()),
								vuviec43.getTuocTuNgay() == null ? "" : (String.valueOf(vuviec43.getTuocTuNgay())),
								vuviec43.getTuocDenNgay() == null ? "" : (String.valueOf(vuviec43.getTuocDenNgay())),
								vuviec43.getCapBacChucVu(), vuviec43.getDonViThuTien(), vuviec43.getTangVatTraLai(),
								vuviec43.getTenDonViLap(), String.valueOf(vuviec43.getLoaiTvtgId()),
								vuviec43.getLoaiTvtg(), vuviec43.getHangTvtg(), vuviec43.getNoiCapTvtg(),
								vuviec43.getHieuLucTvtg(), vuviec43.getTinhTrang(),
								String.valueOf(vuviec43.getSoLuong()), vuviec43.getDonViTinh(), vuviec43.getSoTvtg(),
								vuviec43.getNoiDungHvvp(), vuviec43.getDieuLuat(), vuviec43.getThoiHanTu(),
								vuviec43.getThoiHanDen(), vuviec43.getTangVat() }, count1);

					}

					final ViewBaocaothBb50DAO dao50 = new ViewBaocaothBb50DAO();
					final List<ViewBaocaothBb50> listVuViec50;
					listVuViec50 = dao50.BcHvvpVaPt(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH, toChuc,
							linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt, this.tangVatBCTH, hinhThucXp,
							fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH, this.nhomhvKhacBCTH, this.hanhViVPBCTH,
							xpbs, tuocFrom, tuocTo, chucVu, khoBac, this.nghiDinhBCTH, this.tenNvpBCTH,
							this.DiaChiNvpBCTH, this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH,
							this.soBBBCTH, this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH, this.checkHvvp);
					for (final ViewBaocaothBb50 vuviec50 : listVuViec50) {
						count1++;
						table.addItem(
								new Object[] { String.valueOf(listVuViec50.indexOf(vuviec50) + 1),
										vuviec50.getMaVuViec(), vuviec50.getMaRutgon(),
										String.valueOf(vuviec50.getLoaiBbQd()), vuviec50.getSoBienBan(),
										vuviec50.getTenNguoiNvp(), String.valueOf(vuviec50.getDiaDanhHcId()),
										vuviec50.getDiaChiNvp(), vuviec50.getNgaySinhNvpNhap(),
										vuviec50.getNgheNghiepNvp(), vuviec50.getLoaiPhuongTien(),
										vuviec50.getNoiDungVphc(), vuviec50.getBienKiemSoat(), vuviec50.getHangGplx(),
										vuviec50.getGplx(), vuviec50.getThoiGianVphc(), vuviec50.getDiaDiemVphc(),
										vuviec50.getTangVatTg(), vuviec50.getThoiHanTg(), vuviec50.getHinhThucXp(),
										vuviec50.getTongMucPhat(), vuviec50.getXuPhatBoSung(), vuviec50.getTuNgayXpbs(),
										vuviec50.getDenNgayXpbs(), vuviec50.getBienPhapKhacPhuc(),
										vuviec50.getNgayLapBb(), vuviec50.getTenDonVi(), vuviec50.getTenCanBo(),
										vuviec50.getLinhVucGiaoThong() == null ? ""
												: String.valueOf(vuviec50.getLinhVucGiaoThong()),
										String.valueOf(vuviec50.getToChuc()), vuviec50.getKiemDinh(),
										vuviec50.getTrangThaiNp(), vuviec50.getHanhViVpId(), vuviec50.getLuatTcId(),
										vuviec50.getTuocTuNgay(), vuviec50.getTuocDenNgay(), vuviec50.getCapBacChucVu(),
										vuviec50.getDonViThuTien(), vuviec50.getTangVatTraLai(),
										vuviec50.getTenDonViLap(), String.valueOf(vuviec50.getLoaiTvtgId()),
										vuviec50.getLoaiTvtg(), vuviec50.getHangTvtg(), vuviec50.getNoiCapTvtg(),
										vuviec50.getHieuLucTvtg(), vuviec50.getTinhTrang(),
										String.valueOf(vuviec50.getSoLuong()), vuviec50.getDonViTinh(),
										vuviec50.getSoTvtg(), vuviec50.getNoiDungHvvp(), vuviec50.getDieuLuat(),
										vuviec50.getThoiHanTu(), vuviec50.getThoiHanDen(), vuviec50.getTangVat() },
								count1);

					}

					final ViewBaocaothBb60DAO dao60 = new ViewBaocaothBb60DAO();
					final List<ViewBaocaothBb60> listVuViec60;
					listVuViec60 = dao60.BcHvvpVaPt(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH, toChuc,
							linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt, this.tangVatBCTH, hinhThucXp,
							fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH, this.nhomhvKhacBCTH, this.hanhViVPBCTH,
							xpbs, tuocFrom, tuocTo, chucVu, khoBac, this.nghiDinhBCTH, this.tenNvpBCTH,
							this.DiaChiNvpBCTH, this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH,
							this.soBBBCTH, this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH, this.checkHvvp);

					for (final ViewBaocaothBb60 vuviec60 : listVuViec60) {
						count1++;
						table.addItem(
								new Object[] { String.valueOf(listVuViec60.indexOf(vuviec60) + 1),
										vuviec60.getMaVuViec(), vuviec60.getMaRutgon(),
										String.valueOf(vuviec60.getLoaiBbQd()), vuviec60.getSoBienBan(),
										vuviec60.getTenNguoiNvp(), String.valueOf(vuviec60.getDiaDanhHcId()),
										vuviec60.getDiaChiNvp(), vuviec60.getNgaySinhNvpNhap(),
										vuviec60.getNgheNghiepNvp(), vuviec60.getLoaiPhuongTien(),
										vuviec60.getNoiDungVphc(), vuviec60.getBienKiemSoat(), vuviec60.getHangGplx(),
										vuviec60.getGplx(), vuviec60.getThoiGianVphc(), vuviec60.getDiaDiemVphc(),
										vuviec60.getTangVatTg(), vuviec60.getThoiHanTg(), vuviec60.getHinhThucXp(),
										vuviec60.getTongMucPhat(), vuviec60.getXuPhatBoSung(), vuviec60.getTuNgayXpbs(),
										vuviec60.getDenNgayXpbs(), vuviec60.getBienPhapKhacPhuc(),
										vuviec60.getNgayLapBb(), vuviec60.getTenDonVi(), vuviec60.getTenCanBo(),
										vuviec60.getLinhVucGiaoThong() == null ? ""
												: String.valueOf(vuviec60.getLinhVucGiaoThong()),
										String.valueOf(vuviec60.getToChuc()), vuviec60.getKiemDinh(),
										vuviec60.getTrangThaiNp(), vuviec60.getHanhViVpId(), vuviec60.getLuatTcId(),
										vuviec60.getTuocTuNgay(), vuviec60.getTuocDenNgay(), vuviec60.getCapBacChucVu(),
										vuviec60.getDonViThuTien(), vuviec60.getTangVatTraLai(),
										vuviec60.getTenDonViLap(), String.valueOf(vuviec60.getLoaiTvtgId()),
										vuviec60.getLoaiTvtg(), vuviec60.getHangTvtg(), vuviec60.getNoiCapTvtg(),
										vuviec60.getHieuLucTvtg(), vuviec60.getTinhTrang(),
										String.valueOf(vuviec60.getSoLuong()), vuviec60.getDonViTinh(),
										vuviec60.getSoTvtg(), vuviec60.getNoiDungHvvp(), vuviec60.getDieuLuat(),
										vuviec60.getThoiHanTu(), vuviec60.getThoiHanDen(), vuviec60.getTangVat() },
								count1);

					}

					final ViewBaocaothQd02DAO dao02 = new ViewBaocaothQd02DAO();
					final List<ViewBaocaothQd02> listVuViec02;
					listVuViec02 = dao02.BcHvvpVaPt(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH, toChuc,
							linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt, this.tangVatBCTH, hinhThucXp,
							fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH, this.nhomhvKhacBCTH, this.hanhViVPBCTH,
							xpbs, tuocFrom, tuocTo, chucVu, khoBac, this.nghiDinhBCTH, this.tenNvpBCTH,
							this.DiaChiNvpBCTH, this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH,
							this.soBBBCTH, this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH, this.checkHvvp);
					for (final ViewBaocaothQd02 vuviec02 : listVuViec02) {
						final String stt = String.valueOf(listVuViec02.indexOf(vuviec02) + 1);
						count1++;
						table.addItem(new Object[] { stt, String.valueOf(vuviec02.getMaVuViec()),
								String.valueOf(vuviec02.getMaRutgon()), String.valueOf(vuviec02.getLoaiBbQd()),
								String.valueOf(vuviec02.getSoBienBan()), String.valueOf(vuviec02.getTenNguoiNvp()),
								String.valueOf(vuviec02.getDiaDanhHcId()), vuviec02.getDiaChiNvp(),
								vuviec02.getNgaySinhNvpNhap(), vuviec02.getNgheNghiepNvp(),
								vuviec02.getLoaiPhuongTien(), vuviec02.getNoiDungVphc(), vuviec02.getBienKiemSoat(),
								vuviec02.getHangGplx(), vuviec02.getGplx(), vuviec02.getThoiGianVphc(),
								vuviec02.getDiaDiemVphc(), vuviec02.getTangVatTg(), vuviec02.getThoiHanTg(),
								vuviec02.getHinhThucXp(),
								vuviec02.getTongMucPhat() == null ? null : (String.valueOf(vuviec02.getTongMucPhat())),
								vuviec02.getXuPhatBoSung(),
								vuviec02.getTuNgayXpbs() == null ? "" : (String.valueOf(vuviec02.getTuNgayXpbs())),
								vuviec02.getDenNgayXpbs() == null ? "" : (String.valueOf(vuviec02.getDenNgayXpbs())),
								vuviec02.getBienPhapKhacPhuc(), vuviec02.getNgayLapBb(), vuviec02.getTenDonVi(),
								vuviec02.getTenCanBo(),
								vuviec02.getLinhVucGiaoThong() == null ? ""
										: String.valueOf(vuviec02.getLinhVucGiaoThong()),
								String.valueOf(vuviec02.getToChuc()), vuviec02.getKiemDinh(),
								vuviec02.getTrangThaiNp() == null ? "" : String.valueOf(vuviec02.getTrangThaiNp()),
								vuviec02.getHanhViVpId() == null ? "" : String.valueOf(vuviec02.getHanhViVpId()),
								vuviec02.getLuatTcId() == null ? "" : String.valueOf(vuviec02.getLuatTcId()),
								vuviec02.getTuocTuNgay() == null ? "" : (String.valueOf(vuviec02.getTuocTuNgay())),
								vuviec02.getTuocDenNgay() == null ? "" : (String.valueOf(vuviec02.getTuocDenNgay())),
								vuviec02.getCapBacChucVu(), vuviec02.getDonViThuTien(), vuviec02.getTangVatTraLai(),
								vuviec02.getTenDonViLap(), vuviec02.getLoaiTvtgId(), vuviec02.getLoaiTvtg(),
								vuviec02.getHangTvtg(), vuviec02.getNoiCapTvtg(), vuviec02.getHieuLucTvtg(),
								vuviec02.getTinhTrang(), vuviec02.getSoLuong(), vuviec02.getDonViTinh(),
								vuviec02.getSoTvtg(), vuviec02.getNoiDungHvvp(), vuviec02.getDieuLuat(),
								vuviec02.getThoiHanTu(), vuviec02.getThoiHanDen(), vuviec02.getTangVat() }, count1);

					}

					final ViewBaocaothQd18DAO dao18 = new ViewBaocaothQd18DAO();
					final List<ViewBaocaothQd18> listVuViec18;
					listVuViec18 = dao18.BcHvvpVaPt(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH, toChuc,
							linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt, this.tangVatBCTH, hinhThucXp,
							fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH, this.nhomhvKhacBCTH, this.hanhViVPBCTH,
							xpbs, tuocFrom, tuocTo, chucVu, khoBac, this.nghiDinhBCTH, this.tenNvpBCTH,
							this.DiaChiNvpBCTH, this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH,
							this.soBBBCTH, this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH, this.checkHvvp);
					for (final ViewBaocaothQd18 vuviec18 : listVuViec18) {
						count1++;
						table.addItem(new Object[] { String.valueOf(listVuViec18.indexOf(vuviec18) + 1),
								String.valueOf(vuviec18.getMaVuViec()), String.valueOf(vuviec18.getMaRutgon()),
								String.valueOf(vuviec18.getLoaiBbQd()), String.valueOf(vuviec18.getSoBienBan()),
								String.valueOf(vuviec18.getTenNguoiNvp()), String.valueOf(vuviec18.getDiaDanhHcId()),
								vuviec18.getDiaChiNvp(), vuviec18.getNgaySinhNvpNhap(), vuviec18.getNgheNghiepNvp(),
								vuviec18.getLoaiPhuongTien(), vuviec18.getNoiDungVphc(), vuviec18.getBienKiemSoat(),
								vuviec18.getHangGplx(), vuviec18.getGplx(), vuviec18.getThoiGianVphc(),
								vuviec18.getDiaDiemVphc(), vuviec18.getTangVatTg(), vuviec18.getThoiHanTg(),
								vuviec18.getHinhThucXp(), vuviec18.getTongMucPhat(), vuviec18.getXuPhatBoSung(),
								vuviec18.getTuNgayXpbs(), vuviec18.getDenNgayXpbs(), vuviec18.getBienPhapKhacPhuc(),
								vuviec18.getNgayLapBb(), vuviec18.getTenDonVi(), vuviec18.getTenCanBo(),
								vuviec18.getLinhVucGiaoThong() == null ? ""
										: String.valueOf(vuviec18.getLinhVucGiaoThong()),
								String.valueOf(vuviec18.getToChuc()), vuviec18.getKiemDinh(), vuviec18.getTrangThaiNp(),
								vuviec18.getHanhViVpId(), vuviec18.getLuatTcId(), vuviec18.getTuocTuNgay(),
								vuviec18.getTuocDenNgay(), vuviec18.getCapBacChucVu(), vuviec18.getDonViThuTien(),
								vuviec18.getTangVatTraLai(), vuviec18.getTenDonViLap(),
								String.valueOf(vuviec18.getLoaiTvtgId()), vuviec18.getLoaiTvtg(),
								vuviec18.getHangTvtg(), vuviec18.getNoiCapTvtg(), vuviec18.getHieuLucTvtg(),
								vuviec18.getTinhTrang(), String.valueOf(vuviec18.getSoLuong()), vuviec18.getDonViTinh(),
								vuviec18.getSoTvtg(), vuviec18.getNoiDungHvvp(), vuviec18.getDieuLuat(),
								vuviec18.getThoiHanTu() == null ? "" : (String.valueOf(vuviec18.getThoiHanTu())),
								vuviec18.getThoiHanDen() == null ? "" : (String.valueOf(vuviec18.getThoiHanDen())),
								vuviec18.getTangVat() }, count1);
						;

					}

					final ViewBaocaothQd20DAO dao20 = new ViewBaocaothQd20DAO();
					final List<ViewBaocaothQd20> listVuViec20;
					listVuViec20 = dao20.BcHvvpVaPt(this.danhSachBCTH, this.fromdateBCTH, this.todateBCTH, toChuc,
							linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt, this.tangVatBCTH, hinhThucXp,
							fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH, this.nhomhvKhacBCTH, this.hanhViVPBCTH,
							xpbs, tuocFrom, tuocTo, chucVu, khoBac, this.nghiDinhBCTH, this.tenNvpBCTH,
							this.DiaChiNvpBCTH, this.NoiCapTvBCTH, this.SoGiayToBCTH, this.HangGPLXBCTH, this.BKSBCTH,
							this.soBBBCTH, this.CanBoBCTH, this.loaiBBBCTH, this.thoiHanTuBCTH, this.thoiHanDenBCTH,
							this.TrangThaiXuLyBCTH, this.TinhBCTH, this.QuanBCTH, this.XaBCTH, this.QuocLoBCTH,
							this.TuyenDuongBCTH, this.checkHvvp);
					for (final ViewBaocaothQd20 vuviec20 : listVuViec20) {
						count1++;
						table.addItem(new Object[] { String.valueOf(listVuViec20.indexOf(vuviec20) + 1),
								String.valueOf(vuviec20.getMaVuViec()), String.valueOf(vuviec20.getMaRutgon()),
								String.valueOf(vuviec20.getLoaiBbQd()), String.valueOf(vuviec20.getSoBienBan()),
								String.valueOf(vuviec20.getTenNguoiNvp()), String.valueOf(vuviec20.getDiaDanhHcId()),
								vuviec20.getDiaChiNvp(), vuviec20.getNgaySinhNvpNhap(), vuviec20.getNgheNghiepNvp(),
								vuviec20.getLoaiPhuongTien(), vuviec20.getNoiDungVphc(), vuviec20.getBienKiemSoat(),
								vuviec20.getHangGplx(), vuviec20.getGplx(), vuviec20.getThoiGianVphc(),
								vuviec20.getDiaDiemVphc(), vuviec20.getTangVatTg(), vuviec20.getThoiHanTg(),
								vuviec20.getHinhThucXp(), vuviec20.getTongMucPhat(), vuviec20.getXuPhatBoSung(),
								vuviec20.getTuNgayXpbs(), vuviec20.getDenNgayXpbs(), vuviec20.getBienPhapKhacPhuc(),
								vuviec20.getNgayLapBb(), vuviec20.getTenDonVi(), vuviec20.getTenCanBo(),
								vuviec20.getLinhVucGiaoThong() == null ? ""
										: String.valueOf(vuviec20.getLinhVucGiaoThong()),
								String.valueOf(vuviec20.getToChuc()), vuviec20.getKiemDinh(), vuviec20.getTrangThaiNp(),
								vuviec20.getHanhViVpId(), vuviec20.getLuatTcId(), vuviec20.getTuocTuNgay(),
								vuviec20.getTuocDenNgay(), vuviec20.getCapBacChucVu(), vuviec20.getDonViThuTien(),
								vuviec20.getTangVatTraLai(), vuviec20.getTenDonViLap(),
								String.valueOf(vuviec20.getLoaiTvtgId()), vuviec20.getLoaiTvtg(),
								vuviec20.getHangTvtg(), vuviec20.getNoiCapTvtg(), vuviec20.getHieuLucTvtg(),
								vuviec20.getTinhTrang(), String.valueOf(vuviec20.getSoLuong()), vuviec20.getDonViTinh(),
								vuviec20.getSoTvtg(), vuviec20.getNoiDungHvvp(), vuviec20.getDieuLuat(),
								vuviec20.getThoiHanTu(), vuviec20.getThoiHanDen(), vuviec20.getTangVat() }, count1);

					}

					// final ViewBaocaothQd01DAO dao01 = new
					// ViewBaocaothQd01DAO();
					// final List<ViewBaocaothQd01> listVuViec01;
					// listVuViec01 = dao01.BcHvvpVaPt(this.danhSachBCTH,
					// this.fromdateBCTH, this.todateBCTH, toChuc,
					// linhVuc, ngheNghiep, FromYearOfBirth, ToYearOfBirth, lpt,
					// loaiGiayTo, hinhThucXp,
					// fromMoney, toMoney, hinhThucNp, this.nhomhvBCTH,
					// this.nhomhvKhacBCTH, this.hanhViVPBCTH,
					// xpbs, tuocFrom, tuocTo, chucVu, khoBac,
					// this.nghiDinhBCTH, this.tenNvpBCTH,
					// this.DiaChiNvpBCTH, this.NoiCapTvBCTH, this.SoGiayToBCTH,
					// this.HangGPLXBCTH,
					// this.BKSBCTH, this.soBBBCTH, this.CanBoBCTH,
					// this.loaiBBBCTH, this.thoiHanTuBCTH,
					// this.thoiHanDenBCTH, this.TrangThaiXuLyBCTH,
					// this.TinhBCTH, this.QuanBCTH, this.XaBCTH,
					// this.QuocLoBCTH, this.TuyenDuongBCTH, this.checkHvvp);
					for (final Object i : table.getItemIds()) {

						final Property propertyMaVV = table.getContainerProperty(i, "MA_RUTGON");
						final Row row43 = sheet.createRow(rowNum++);
						final Cell A5 = row43.createCell(0);
						A5.setCellValue(row43.getRowNum() - 2);
						final Cell B5 = row43.createCell(1);
						B5.setCellValue((String) propertyMaVV.getValue());

						final Property propertyLOAI_BB_QD = table.getContainerProperty(i, "LOAI_BB_QD");
						final long loaiBB = Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim());

						final Cell C5 = row43.createCell(2);
						if (loaiBB == 43) {
							C5.setCellValue("Biên bản vi phạm hành chính");
						} else if (loaiBB == 2) {
							C5.setCellValue("Quyết định xử phạt vi phạm hành chính");
						} else if (loaiBB == 1) {
							C5.setCellValue("Quyết định xử phạt vi phạm hành chính không lập biên bản");
						} else if (loaiBB == 18) {
							C5.setCellValue("Quyết định tạm giữ tang vật phương tiện");
						} else if (loaiBB == 20) {
							C5.setCellValue("Quyết định trả lại tang vật phương tiện");
						} else if (loaiBB == 60) {
							C5.setCellValue("Biên bản trả lại tang vật phương tiện");
						} else if (loaiBB == 50) {
							C5.setCellValue("Biên bản tạm giữ tang vật phương tiện");
						}

						final Property propertySoBB = table.getContainerProperty(i, "SO_BIEN_BAN");
						final Cell D5 = row43.createCell(3);
						D5.setCellValue(propertySoBB.getValue().toString());

						final Property propertyTC = table.getContainerProperty(i, "TO_CHUC");
						final Cell E5 = row43.createCell(4);
						if (propertyTC.getValue() != null) {
							if (Long.parseLong(propertyTC.getValue().toString().trim()) == 0) {
								E5.setCellValue("Cá nhân");
							} else if (Long.parseLong(propertyTC.getValue().toString().trim()) == 1) {
								E5.setCellValue("Tổ chức");
							}
						} else {
							E5.setCellValue("");
						}

						final Property propertyTen = table.getContainerProperty(i, "TEN_NGUOI_NVP");
						final Cell F5 = row43.createCell(5);
						F5.setCellValue((String) propertyTen.getValue());
						final Property propertyThoiGianVphc = table.getContainerProperty(i, "THOI_GIAN_VPHC");
						final Cell G5 = row43.createCell(6);
						G5.setCellValue((String) propertyThoiGianVphc.getValue());

						final Property propertyDiaChi = table.getContainerProperty(i, "DIA_CHI_NVP");
						final Cell H5 = row43.createCell(7);
						H5.setCellValue((String) propertyDiaChi.getValue());

						final Cell I5 = row43.createCell(8);
						I5.setCellValue("");

						final Property propertyNgaySinh = table.getContainerProperty(i, "NGAY_SINH_NVP_NHAP");
						final Cell J5 = row43.createCell(9);
						J5.setCellValue((String) propertyNgaySinh.getValue());

						final Cell K5 = row43.createCell(10);
						K5.setCellValue("");

						final Property propertyNgheNghiep = table.getContainerProperty(i, "NGHE_NGHIEP_NVP");
						final Cell L5 = row43.createCell(11);
						L5.setCellValue((String) propertyNgheNghiep.getValue());

						final Property propertylpt = table.getContainerProperty(i, "LOAI_PHUONG_TIEN");
						final Cell M5 = row43.createCell(12);
						M5.setCellValue((String) propertylpt.getValue());

						final Property propertyBKS = table.getContainerProperty(i, "BIEN_KIEM_SOAT");
						final Cell N5 = row43.createCell(13);
						N5.setCellValue((String) propertyBKS.getValue());

						final Property propertydiaDiemVPHC = table.getContainerProperty(i, "DIA_DIEM_VPHC");
						final Cell O5 = row43.createCell(14);
						O5.setCellValue((String) propertydiaDiemVPHC.getValue());

						final Property propertyNoiDungVphc = table.getContainerProperty(i, "NOI_DUNG_VPHC");
						final Cell P5 = row43.createCell(15);
						String NoiDung = "";
						if (propertyNoiDungVphc.getValue() != null) {
							NoiDung = (String) propertyNoiDungVphc.getValue();
						}
						P5.setCellValue(NoiDung);

						String sbHvvp = "";
						final Cell Q5 = row43.createCell(16);
						String NdHvpp = "";
						if (propertyNoiDungVphc.getValue() != null) {
							NdHvpp = (String) propertyNoiDungVphc.getValue();
						}
						if (loaiBB != 2 && loaiBB != 1) {
							if (NdHvpp.contains("quy định tại") == true) {
								sbHvvp = substrHvvp(typefile(NdHvpp));
							} else {
								sbHvvp = NdHvpp;
							}
						} else {
							sbHvvp = "";
						}
						final Property propertyDieuLuat = table.getContainerProperty(i, "DIEU_LUAT");
						Q5.setCellValue((String) propertyDieuLuat.getValue());

						final Property propertyTvTg = table.getContainerProperty(i, "TANG_VAT_TG");
						final Cell R5 = row43.createCell(17);
						R5.setCellValue(tenNhom);

						String tangVat = "";
						if (tangVat == null) {
							tangVat = "";
						} else {
							tangVat = (String) propertyTvTg.getValue();
							if (tangVat.contains("nơi cấp") == true) {
								substr(tangVat);
							} else {
								tangVat = "";
							}
						}

						final Property ppHang = table.getContainerProperty(i, "HANG_TVTG");
						final Property ppHieuLuc = table.getContainerProperty(i, "HIEU_LUC_TVTG");
						final Property ppTinhTrang = table.getContainerProperty(i, "TINH_TRANG");
						final Property ppSoLuong = table.getContainerProperty(i, "SO_LUONG");
						final Property ppDonViTinh = table.getContainerProperty(i, "DON_VI_TINH");
						final Property ppNoiCap = table.getContainerProperty(i, "NOI_CAP_TVTG");
						final Property ppLoaiTvtg = table.getContainerProperty(i, "LOAI_TVTG");
						final Cell S5 = row43.createCell(18);
						String tangvat = "";
						if ((this.cmbLoaiGiayTo.getValue() != "" && this.cmbLoaiGiayTo.getValue() != null)) {
							if (this.cmbLoaiGiayTo.getValue() != "Tất cả") {
								if (this.cmbLoaiGiayTo.getValue() == "GPLX") {
									tangvat = "01 giấy phép lái xe hạng "
											+ (ppHang.getValue() == null ? ""
													: (ppHang.getValue().toString().trim()) + " ")
											+ (ppTinhTrang.getValue() == null ? ""
													: (ppTinhTrang.getValue().toString().trim()) + " ")
											+ (ppHieuLuc.getValue() == null ? ""
													: (ppHieuLuc.getValue() == "" ? ""
															: (" giá trị đến "
																	+ ppHieuLuc.getValue().toString().trim())))
											+ " nơi cấp " + ppNoiCap.getValue().toString().trim() + "; ";
								} else if (this.cmbLoaiGiayTo.getValue() == "Đăng ký") {
									tangvat = "01 giấy đăng ký xe:" + " "
											+ (ppTinhTrang.getValue() == null ? ""
													: (ppTinhTrang.getValue().toString().trim()))
											+ " nơi cấp" + ppNoiCap.getValue().toString().trim() + "; ";
								} else if (this.cmbLoaiGiayTo.getValue() == "Kiểm định") {
									tangvat = "01 giấy chứng nhận kiểm định an toàn kỹ thuật và bảo vệ môi trường:"
											+ " "
											+ ppTinhTrang.getValue().toString()
													.trim()
											+ (ppHieuLuc
													.getValue() == null
															? ""
															: (ppHieuLuc.getValue() == "" ? ""
																	: (" có giá trị đến "
																			+ ppHieuLuc.getValue().toString().trim())))
											+ "; ";
								} else if (this.cmbLoaiGiayTo.getValue() == "Phương tiện") {
									tangvat = "01 Phương tiện" + " " + (ppTinhTrang.getValue() == null ? ""
											: (ppTinhTrang.getValue().toString().trim())) + "; ";
								} else if (this.cmbLoaiGiayTo.getValue() == "Khác") {
									final String Khac = (ppSoLuong.getValue() == null ? ""
											: (Long.parseLong(ppSoLuong.getValue().toString()) == 0 ? ""
													: (ppSoLuong.getValue().toString().trim()) + " "))
											+ (ppDonViTinh.getValue() == null ? ""
													: (ppDonViTinh.getValue().toString().trim()) + " ")
											+ (ppLoaiTvtg.getValue() == "" ? ""
													: (ppLoaiTvtg.getValue().toString().trim()) + " ")
											+ (ppTinhTrang.getValue() == null ? ""
													: (ppTinhTrang.getValue().toString().trim()));
									tangvat = Khac.trim() + ";";
								} else {

									tangvat = (String) propertyTvTg.getValue();
								}
							} else {
								tangvat = (String) propertyTvTg.getValue();
							}
						}
						S5.setCellValue(tangvat);

						final Cell T5 = row43.createCell(19);
						if (ppHang.getValue() != null) {
							if (ppHang.getValue().toString().contains("N/A")) {
								T5.setCellValue("");
							} else {
								T5.setCellValue((String) ppHang.getValue());
							}
						}

						final Cell U5 = row43.createCell(20);
						String gplx = "";
						if ((this.cmbLoaiGiayTo.getValue() != "" && this.cmbLoaiGiayTo.getValue() != null)) {
							if (this.cmbLoaiGiayTo.getValue() != "Tất cả") {
								if (this.cmbLoaiGiayTo.getValue() == "GPLX") {
									gplx = "01 giấy phép lái xe hạng "
											+ (ppHang.getValue() == null ? ""
													: (ppHang.getValue().toString().trim()) + " ")
											+ (ppTinhTrang.getValue() == null ? ""
													: (ppTinhTrang.getValue().toString().trim()) + " ")
											+ (ppHieuLuc.getValue() == null ? ""
													: (ppHieuLuc.getValue() == "" ? ""
															: (" giá trị đến "
																	+ ppHieuLuc.getValue().toString().trim())))
											+ " nơi cấp " + ppNoiCap.getValue().toString().trim() + "; ";
								} else if (this.cmbLoaiGiayTo.getValue() == "Đăng ký") {
									gplx = "01 giấy đăng ký xe:" + " "
											+ (ppTinhTrang.getValue() == null ? ""
													: (ppTinhTrang.getValue().toString().trim()))
											+ " nơi cấp" + ppNoiCap.getValue().toString().trim() + "; ";
								} else if (this.cmbLoaiGiayTo.getValue() == "Kiểm định") {
									gplx = "01 giấy chứng nhận kiểm định an toàn kỹ thuật và bảo vệ môi trường:" + " "
											+ (ppTinhTrang.getValue() == null ? ""
													: (ppTinhTrang.getValue().toString().trim()))
											+ (ppHieuLuc.getValue().toString().trim() == "" ? ""
													: (" có giá trị đến " + ppHieuLuc.getValue().toString()))
											+ "; ";
									// } else if (this.cmbLoaiGiayTo.getValue()
									// ==
									// "Phương tiện") {
									// gplx = "01 Phương tiện" + " "
									// + (vuviec43.getTinhTrang() == null ? "" :
									// (vuviec43.getTinhTrang().trim()))
									// + "; ";
								} else if (this.cmbLoaiGiayTo.getValue() == "Khác") {
									final String Khac = (ppSoLuong.getValue() == null ? ""
											: (Long.parseLong(ppSoLuong.getValue().toString()) == 0 ? ""
													: (ppSoLuong.getValue().toString().trim()) + " "))
											+ (ppDonViTinh.getValue() == null ? ""
													: (ppDonViTinh.getValue().toString().trim()) + " ")
											+ (ppLoaiTvtg.getValue() == "" ? ""
													: (ppLoaiTvtg.getValue().toString().trim()) + " ")
											+ (ppTinhTrang.getValue() == null ? ""
													: (ppTinhTrang.getValue().toString().trim()));
									gplx = Khac.trim() + ";";
								}
							} else {
								if (ppTinhTrang.getValue() != null) {
									if (ppTinhTrang.getValue().toString().contains("N/A")) {
										gplx = "";
									} else if (ppTinhTrang.getValue().toString().contains("BKS")) {
										gplx = "";
									} else {
										gplx = (String) ppTinhTrang.getValue();
									}
								}

							}
						} else {
							if (ppTinhTrang.getValue() != null) {
								if (ppTinhTrang.getValue().toString().contains("N/A")) {
									gplx = "";
								} else {
									gplx = (String) ppTinhTrang.getValue();
								}
							}
						}
						U5.setCellValue(gplx);
						final Cell V5 = row43.createCell(21);
						String Hieuluc = "";

						if (ppHieuLuc.getValue() != null) {
							if (ppHieuLuc.getValue().toString().contains("N/A")) {
								Hieuluc = "";
							} else {
								Hieuluc = ppHieuLuc.getValue().toString();
							}

						}
						V5.setCellValue(Hieuluc);

						final Cell W5 = row43.createCell(22);
						if (ppNoiCap.getValue() != null) {
							if (ppNoiCap.getValue().toString().contains("N/A")) {
								W5.setCellValue("");
							} else {
								W5.setCellValue((String) ppNoiCap.getValue());
							}
						}

						final Property propertyThoiHanTg = table.getContainerProperty(i, "THOI_HAN_TG");
						final Cell X5 = row43.createCell(23);
						X5.setCellValue((String) propertyThoiHanTg.getValue());

						final Property propertyTrHTXP = table.getContainerProperty(i, "HINH_THUC_XP");
						final Cell Y5 = row43.createCell(24);
						Y5.setCellValue((String) propertyTrHTXP.getValue());

						final Property propertyTienNp = table.getContainerProperty(i, "TONG_MUC_PHAT");
						final Cell Z5 = row43.createCell(25);
						// if (this.txtPhatTienTu.isEmpty() &&
						// this.txtPhatTienDen.isEmpty()) {
						// Z5.setCellValue("");
						// } else {
						final NumberFormat fmMoney = NumberFormat.getCurrencyInstance(new Locale("vn", "VN"));
						final DecimalFormatSymbols formatSym = ((DecimalFormat) fmMoney).getDecimalFormatSymbols();
						formatSym.setCurrencySymbol("");
						((DecimalFormat) fmMoney).setDecimalFormatSymbols(formatSym);
						if (propertyTienNp.getValue() != null && propertyTienNp.getValue() != "") {
							Z5.setCellValue(fmMoney.format(Long.parseLong((String) propertyTienNp.getValue()))
									.replace(",", "."));
						}

						final Property propertyTrThaiNp = table.getContainerProperty(i, "TRANG_THAI_NP");
						final Cell AA5 = row43.createCell(26);
						String trangThai = "";
						if (propertyTrThaiNp.getValue() != null && propertyTrThaiNp.getValue() != "") {
							if (Integer.parseInt(propertyTrThaiNp.getValue().toString().trim()) == 3) {
								trangThai = "Đã thanh toán qua DVC";
							} else if (Integer.parseInt(propertyTrThaiNp.getValue().toString().trim()) == 4) {
								trangThai = "Đã gửi SMS";
							} else if (Integer.parseInt(propertyTrThaiNp.getValue().toString().trim()) == 5) {
								trangThai = "Đã thanh toán trực tiếp";
							}
						}
						AA5.setCellValue(trangThai);

						final SimpleDateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");
						final Property propertyXpbs = table.getContainerProperty(i, "XU_PHAT_BO_SUNG");
						final Cell AB5 = row43.createCell(27);
						AB5.setCellValue((String) propertyXpbs.getValue());

						final Property PptuNgayXpbs = table.getContainerProperty(i, "TU_NGAY_XPBS");
						final Cell AC5 = row43.createCell(28);
						String FromDateXpbs = "";
						if (PptuNgayXpbs.getValue() != null && PptuNgayXpbs.getValue() != "") {
							FromDateXpbs = (String) PptuNgayXpbs.getValue();
							final DateFormat formatDateXpbs = new SimpleDateFormat("yyyy-MM-dd");
							Date dateXpbs;
							try {
								dateXpbs = formatDateXpbs.parse(FromDateXpbs);
								AC5.setCellValue(dfm.format(dateXpbs));
							} catch (final ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						final Property PpDenNgayXpbs = table.getContainerProperty(i, "DEN_NGAY_XPBS");
						final Cell AD5 = row43.createCell(29);
						String ToDateXpbs = "";
						if (PpDenNgayXpbs.getValue() != null && PpDenNgayXpbs.getValue() != "") {
							ToDateXpbs = (String) PpDenNgayXpbs.getValue();
							final DateFormat formatDateXpbs = new SimpleDateFormat("yyyy-MM-dd");
							Date dateXpbs;
							try {
								dateXpbs = formatDateXpbs.parse(ToDateXpbs);
								AD5.setCellValue(dfm.format(dateXpbs));
							} catch (final ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						final Property PpBPKP = table.getContainerProperty(i, "BIEN_PHAP_KHAC_PHUC");
						final Cell AE5 = row43.createCell(30);
						AE5.setCellValue((String) PpBPKP.getValue());

						final Property PpTvtl = table.getContainerProperty(i, "TANG_VAT_TRA_LAI");
						final Cell AT5 = row43.createCell(31);
						AT5.setCellValue((String) PpTvtl.getValue());

						final Property PpNgayLapBB = table.getContainerProperty(i, "NGAY_LAP_BB");
						final Cell AF5 = row43.createCell(32);
						Date newDate;
						if (PpNgayLapBB.getValue() != null) {
							newDate = (Date) PpNgayLapBB.getValue();
							AF5.setCellValue(dfm.format(newDate));
						}

						final Property PpTenDvLap = table.getContainerProperty(i, "TEN_DON_VI_LAP");
						final Cell AM5 = row43.createCell(33);
						AM5.setCellValue((String) PpTenDvLap.getValue());

						final Property PpTenDv = table.getContainerProperty(i, "TEN_DON_VI");
						final Cell AG5 = row43.createCell(34);
						AG5.setCellValue((String) PpTenDv.getValue());

						final Property PpTenCb = table.getContainerProperty(i, "TEN_CAN_BO");
						final Cell AH5 = row43.createCell(35);
						AH5.setCellValue((String) PpTenCb.getValue());

						final Property PpCbcv = table.getContainerProperty(i, "CAP_BAC_CHUC_VU");
						final Cell AI5 = row43.createCell(36);
						String chucvu = "";
						if (PpCbcv.getValue() != null) {
							if (PpCbcv.getValue().toString().trim().contains(",")) {
								chucvu = PpCbcv.getValue().toString().trim().split(",", 2)[1].trim();
							}
						}
						AI5.setCellValue(chucvu);

						final Cell AJ5 = row43.createCell(37);
						AJ5.setCellValue(PpTenCb.getValue().toString().trim());
						final Cell AK5 = row43.createCell(38);
						AK5.setCellValue(this.diaBanVp);

						final Property PpLinhVuc = table.getContainerProperty(i, "LINH_VUC_GIAO_THONG");
						final Cell AL5 = row43.createCell(39);
						String linhVucGt = "";
						if (PpLinhVuc.getValue() != "") {
							if (Integer.parseInt(PpLinhVuc.getValue().toString().trim()) == 1) {
								linhVucGt = "Đường bộ";
							} else if (Integer.parseInt(PpLinhVuc.getValue().toString().trim()) == 2) {
								linhVucGt = "Đường sắt";
							} else if (Integer.parseInt(PpLinhVuc.getValue().toString().trim()) == 4) {
								linhVucGt = "Đường thủy";
							}
						}
						AL5.setCellValue(linhVucGt);

					}
				}
			}
			try {
				final File tempFile = File.createTempFile("Bao_Cao_TongHop.", ".xlsx");
				final FileOutputStream tempOutputStream = new FileOutputStream(tempFile);
				workbook.write(tempOutputStream);
				try (OutputStream os = tempOutputStream) {
					// workbook.write(os);

					final Resource resourceBaoCao = new FileResource(tempFile);
					Page.getCurrent().open(resourceBaoCao, "_blank", false);

				} catch (final IOException e) {
					e.printStackTrace();
				}
			} catch (final IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Done");
		} else

		{
			try {
				System.out.println("Create file excel");
				final XSSFWorkbook workbook = new XSSFWorkbook();
				final XSSFSheet sheet = workbook.createSheet("Báo Cáo Tổng Hợp");
				final File tempFile = File.createTempFile("Bao_Cao_TongHop.", ".xlsx");
				final FileOutputStream tempOutputStream = new FileOutputStream(tempFile);
				workbook.write(tempOutputStream);
				try (OutputStream os = tempOutputStream) {
					// workbook.write(os);

					final Resource resourceBaoCao = new FileResource(tempFile);
					// this.browserFrame.setSource(res1);
					Page.getCurrent().open(resourceBaoCao, "_blank", false);
					// this.fileDownloader.extend(this.btnXuatChoDp);
					// this.fileDownloader = null;
				} catch (final IOException e) {
					e.printStackTrace();
				}
			} catch (final IOException e) {
				e.printStackTrace();
			}
			System.out.println("Done");
		}

	}

	public String Regex(final String a) {
		String c = "";
		final String pattern = "nơi cấp.*?;+";

		final Pattern r = Pattern.compile(pattern);

		// Now create matcher object.
		final Matcher m = r.matcher(a);

		while (m.find() == true) {
			c = m.group(0);
			System.out.print(c);
		}
		return c;
	}

	/**
	 * Event handler delegate method for the {@link XdevTextField}
	 * {@link #txtTimKiem}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void txtTimKiem_valueChange(final Property.ValueChangeEvent event) {
		try {
		} catch (final Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevButton}
	 * {@link #btnTheoTieuChi}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnTheoTieuChi_buttonClick(final Button.ClickEvent event) {
		UI.getCurrent().getSession().setAttribute("DonViBCTH", this.danhSachBCTH);
		UI.getCurrent().getSession().setAttribute("fromdateBCTH", this.fromdateBCTH);
		UI.getCurrent().getSession().setAttribute("todateBCTH", this.todateBCTH);
		UI.getCurrent().getSession().setAttribute("tuNgayBCTH", this.tuNgayBCTH);
		UI.getCurrent().getSession().setAttribute("denNgayBCTH", this.denNgayBCTH);
		UI.getCurrent().getSession().setAttribute("nhomHvBCTH", this.nhomhvBCTH);
		UI.getCurrent().getSession().setAttribute("nhomHvKhacBCTH", this.nhomhvKhacBCTH);
		UI.getCurrent().getSession().setAttribute("ToChucBCTH", this.ToChucBCTH);
		UI.getCurrent().getSession().setAttribute("LinhVucBCTH", this.LinhVucBCTH);
		UI.getCurrent().getSession().setAttribute("loaiBBBCTH", this.loaiBBBCTH);
		UI.getCurrent().getSession().setAttribute("tenNvpBCTH", this.tenNvpBCTH);
		UI.getCurrent().getSession().setAttribute("DiaChiNvpBCTH", this.DiaChiNvpBCTH);
		UI.getCurrent().getSession().setAttribute("ngheNghiepBCTH", this.ngheNghiepNvpBCTH);
		UI.getCurrent().getSession().setAttribute("NoiCapTvBCTH", this.NoiCapTvBCTH);
		UI.getCurrent().getSession().setAttribute("SoGiayToBCTH", this.SoGiayToBCTH);
		UI.getCurrent().getSession().setAttribute("HangGPLXBCTH", this.HangGPLXBCTH);
		UI.getCurrent().getSession().setAttribute("BKSBCTH", this.BKSBCTH);
		UI.getCurrent().getSession().setAttribute("SoBBBCTH", this.soBBBCTH);
		UI.getCurrent().getSession().setAttribute("CanBoBCTH", this.CanBoBCTH);
		UI.getCurrent().getSession().setAttribute("tuoiBCTH", this.tuoiBCTH);
		UI.getCurrent().getSession().setAttribute("TuoiDenBCTH", this.tuoiDenBCTH);
		UI.getCurrent().getSession().setAttribute("thoiHanTuBCTH", this.thoiHanTuBCTH);
		UI.getCurrent().getSession().setAttribute("thoiHanDenBCTH", this.thoiHanDenBCTH);
		UI.getCurrent().getSession().setAttribute("tangVatBCTH", this.tangVatBCTH);
		UI.getCurrent().getSession().setAttribute("loaiPhuongTienBCTH", this.loaiPhuongTienBCTH);
		UI.getCurrent().getSession().setAttribute("TrangThaiXuLyBCTH", this.TrangThaiXuLyBCTH);
		UI.getCurrent().getSession().setAttribute("hinhThucPhatBCTH", this.hinhThucPhatBCTH);
		UI.getCurrent().getSession().setAttribute("TienPhatTuBCTH", this.TienPhatTuBCTH);
		UI.getCurrent().getSession().setAttribute("TienPhatDenBCTH", this.TienPhatDenBCTH);
		UI.getCurrent().getSession().setAttribute("HinhThucNopPhatBCTH", this.HinhThucNopPhatBCTH);
		UI.getCurrent().getSession().setAttribute("NpTrucTuyenBCTH", this.NpTrucTuyenBCTH);
		UI.getCurrent().getSession().setAttribute("xpbsBCTH", this.xpbsBCTH);
		UI.getCurrent().getSession().setAttribute("tuocTuNgayBCTH", this.tuocTuNgayBCTH);
		UI.getCurrent().getSession().setAttribute("tuocDenNgayBCTH", this.tuocDenNgayBCTH);
		UI.getCurrent().getSession().setAttribute("nghiDinhBCTH", this.nghiDinhBCTH);
		UI.getCurrent().getSession().setAttribute("hanhViVPBCTH", this.hanhViVPBCTH);
		UI.getCurrent().getSession().setAttribute("tuNamBCTH", this.tuNamBCTH);
		UI.getCurrent().getSession().setAttribute("denNamBCTH", this.denNamBCTH);
		UI.getCurrent().getSession().setAttribute("chucVuBCTH", this.chucVuBCTH);
		UI.getCurrent().getSession().setAttribute("khobacBCTH", this.khobacBCTH);
		UI.getCurrent().getSession().setAttribute("TinhBCTH", this.TinhBCTH);
		UI.getCurrent().getSession().setAttribute("QuanBCTH", this.QuanBCTH);
		UI.getCurrent().getSession().setAttribute("XaBCTH", this.XaBCTH);
		UI.getCurrent().getSession().setAttribute("QuocLoBCTH", this.QuocLoBCTH);
		UI.getCurrent().getSession().setAttribute("TuyenDuongBCTH", this.TuyenDuongBCTH);
		UI.getCurrent().getSession().setAttribute("checkHvvpBCTH", this.checkHvvp);
		UI.getCurrent().getSession().setAttribute("diaBanVpBCTH", this.diaBanVp);
		if (this.cmbNhomHv.getValue() != null && this.cmbNhomHv.getValue() != "") {
			if (this.cmbNhomHv.getValue().toString().contains("Tất cả")) {
				UI.getCurrent().getSession().setAttribute("tenNhomBCTH", "");
			} else {
				UI.getCurrent().getSession().setAttribute("tenNhomBCTH", this.cmbNhomHv.getValue().toString());
			}
		} else {
			UI.getCurrent().getSession().setAttribute("tenNhomBCTH", "");
		}
		final Window w = new Window();
		w.center();
		w.setWidth(750, UNITS_PIXELS);
		w.setHeight(550, UNITS_PIXELS);
		w.setResizable(false);
		w.setClosable(true);
		w.setModal(true);
		w.setCaption("XUẤT BÁO CÁO THEO TIÊU CHÍ");
		w.setContent(new ChonTieuChiBaoCaoView());
		UI.getCurrent().addWindow(w);
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbNhomHv}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbNhomHv_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbNhomHv.getValue() != null && this.cmbNhomHv.getValue() != "") {
			if (this.cmbNhomHv.getValue() != "Tất cả") {
				if (this.cmbNhomHv.getValue().equals("Vi phạm về nồng độ cồn khi điều khiển phương tiện")) {
					this.nhomhvBCTH = "47, 53, 55, 110, 113, 119, 194, 180, 191, 533, 534, 56, 120, 159, 164, 195";
					this.nhomhvKhacBCTH = "";
				} else if (this.cmbNhomHv.getValue()
						.equals("Người điều khiển phương tiện trên đường mà trong cơ thể có chất ma túy")) {
					this.nhomhvBCTH = "57, 122, 535, 58, 121, 163";
					this.nhomhvKhacBCTH = "";
				} else if (this.cmbNhomHv.getValue().equals("Điều khiển phương tiện lạng lách đánh võng")) {
					this.nhomhvBCTH = "186, 49, 54, 115, 123, 504 ";
					this.nhomhvKhacBCTH = "";
				} else if (this.cmbNhomHv.getValue().equals("Đi sai làn đường, phần đường")) {
					this.nhomhvBCTH = "137, 165, 196, 554, 556, 2, 560, 5,6, 8, 13, 22, 29, 40, 42, 48, 62, 66, 67, 75, 78, 82, 93, 112, 128, 130, 131, 132, 134, 137, 147, 169, 205, 580, 589, 598, 125, 127";
					this.nhomhvKhacBCTH = "";
				} else if (this.cmbNhomHv.getValue().equals("Tránh vượt sai quy định")) {
					this.nhomhvBCTH = "70, 15, 35, 39, 41, 48, 60, 94, 101, 102, 112, 142, 140, 158, 168, 196";
					this.nhomhvKhacBCTH = "";
				} else if (this.cmbNhomHv.getValue().equals("Dừng đỗ sai quy định")) {
					this.nhomhvBCTH = "170, 556, 557, 5, 6, 13, 14, 30, 31, 46, 75, 79, 82, 90, 100, 129, 130, 131, 132, 133, 134, 148, 153, 156, 158, 169, 174, 175, 385, 562, 12, 35, 48, 54, 92, 112, 118, 123, 141, 162, 166, 193, 333";
					this.nhomhvKhacBCTH = "";
				} else if (this.cmbNhomHv.getValue().equals("Vi phạm quy định về đội mũ bảo hiểm")) {
					this.nhomhvBCTH = "83, 84, 190, 189, 219";
					this.nhomhvKhacBCTH = "";
				} else if (this.cmbNhomHv.getValue().equals("Chở quá trọng tải hàng hóa")) {
					this.nhomhvBCTH = "356, 389, 399, 408, 340";
					this.nhomhvKhacBCTH = "";
				} else if (this.cmbNhomHv.getValue().equals("Chở quá số người quy định")) {
					this.nhomhvBCTH = "3, 85, 178, 313, 328, 88";
					this.nhomhvKhacBCTH = "";
				} else if (this.cmbNhomHv.getValue().equals(
						"Không chấp hành tín hiệu của đèn giao thông, hiệu lệnh và hướng dẫn của người điều khiển giao thông hoặc người kiểm soát giao thông")) {
					this.nhomhvBCTH = "36, 37, 104, 105, 154, 155, 182, 185, 198, 210, 196, 202, 54, 503";
					this.nhomhvKhacBCTH = "";
				} else if (this.cmbNhomHv.getValue().equals("Đi vào đường cấm, đi ngược chiều đường một chiều")) {
					this.nhomhvBCTH = " 38, 107, 146, 188, 205, 48, 112, 158";
					this.nhomhvKhacBCTH = "";
				} else if (this.cmbNhomHv.getValue().equals("Chạy quá tốc độ cho phép")) {
					this.nhomhvBCTH = "9, 44, 45, 48, 50, 77, 99, 111, 112, 117, 135, 144, 157, 158,  49, 54";
					this.nhomhvKhacBCTH = "";
				} else if (this.cmbNhomHv.getValue().equals("Vi phạm quy định về niên hạn sử dụng của phương tiện")) {
					this.nhomhvBCTH = "249, 456, 412";
					this.nhomhvKhacBCTH = "";
				} else if (this.cmbNhomHv.getValue()
						.equals("Vi phạm quy định về đóng mới, hoán cải, sửa chữa phương tiện")) {
					this.nhomhvBCTH = "422, 423, 237, 238, 239, 240, 241, 242, 247, 250, 428, 440, 460, 461, 233, 234, 235, 236, 243, 244, 245, 246, 248, 249, 251, 254, 255, 256, 257, 258, 260, 261, 262, 263, 264, 265, 266, 429, 430, 437, 452, 457, 458, 462, 467, 468, 456, 259, 420, 421";
					this.nhomhvKhacBCTH = "";
				} else if (this.cmbNhomHv.getValue().equals("Vi phạm quy định về GPLX")) {
					this.nhomhvBCTH = " 287, 288, 294, 295, 296, 299, 300, 301, 302, 303, 304, 305, 308, 431, 293, 298, 657";
					this.nhomhvKhacBCTH = "";
				} else if (this.cmbNhomHv.getValue().equals("Vi phạm quy định xếp dỡ hàng hóa trên phương tiện")) {
					this.nhomhvBCTH = "179, 96, 212, 318, 319, 332, 389";
					this.nhomhvKhacBCTH = "";
				} else if (this.cmbNhomHv.getValue().equals("Vi phạm quy định về vận chuyển người, hành khách")) {
					this.nhomhvBCTH = "329, 330, 331, 333, 334, 515, 338, 339, 346, 347, 350, 351, 352, 631, 406, 357";
					this.nhomhvKhacBCTH = "";
				} else if (this.cmbNhomHv.getValue().equals("Hành vi khác")) {
					this.nhomhvKhacBCTH = "47, 53, 55, 110, 113, 119, 194, 180, 191, 533, 534, 56, 120, 159, 164, 195"
							+ ", " + "57, 122, 535, 58, 121, 163" + ", " + "186, 49, 54, 115, 123, 504 " + ", "
							+ "137, 165, 196, 554, 556, 2, 560, 5,6, 8, 13, 22, 29, 40, 42, 48, 62, 66, 67, 75, 78, 82, 93, 112, 128, 130, 131, 132, 134, 137, 147, 169, 205, 580, 589, 598, 125, 127"
							+ ", " + "70, 15, 35, 39, 41, 48, 60, 94, 101, 102, 112, 142, 140, 158, 168, 196" + ", "
							+ "170, 556, 557, 5, 6, 13, 14, 30, 31, 46, 75, 79, 82, 90, 100, 129, 130, 131, 132, 133, 134, 148, 153, 156, 158, 169, 174, 175, 385, 562, 12, 35, 48, 54, 92, 112, 118, 123, 141, 162, 166, 193, 333"
							+ ", " + "83, 84, 190, 189, 219" + ", " + "356, 389, 399, 408, 340" + ", "
							+ "3, 85, 178, 313, 328, 88" + ", "
							+ "36, 37, 104, 105, 154, 155, 182, 185, 198, 210, 196, 202, 54, 503" + ", "
							+ "38, 107, 146, 188, 205, 48, 112, 158" + ", "
							+ "9, 44, 45, 48, 50, 77, 99, 111, 112, 117, 135, 144, 157, 158,  49, 54" + ", "
							+ "249, 456, 412" + ", "
							+ "422, 423, 237, 238, 239, 240, 241, 242, 247, 250, 428, 440, 460, 461, 233, 234, 235, 236, 243, 244, 245, 246, 248, 249, 251, 254, 255, 256, 257, 258, 260, 261, 262, 263, 264, 265, 266, 429, 430, 437, 452, 457, 458, 462, 467, 468, 456, 259, 420, 421"
							+ ", "
							+ " 287, 288, 294, 295, 296, 299, 300, 301, 302, 303, 304, 305, 308, 431, 293, 298, 657"
							+ ", " + "179, 96, 212, 318, 319, 332, 389" + ", "
							+ "329, 330, 331, 333, 334, 515, 338, 339, 346, 347, 350, 351, 352, 631, 406, 357";
					this.nhomhvBCTH = "";
				}
				long nghiDinh = 0;
				if (this.cmbNghiDinh.getSelectedItem() != null) {
					nghiDinh = this.cmbNghiDinh.getSelectedItem().getBean().getSo();
				}
				if (this.cmbLoaiPt.getSelectedItem() != null) {
					this.cmbHvvp.setContainerDataSource(HanhViViPham.class,
							DAOs.get(HanhViViPhamDAO.class).getListByNhomHv(this.nhomhvBCTH, this.nhomhvKhacBCTH,
									this.cmbLoaiPt.getSelectedItem().getBean().getId(), nghiDinh));
				} else if (this.cmbLoaiPt.getSelectedItem() == null) {
					this.cmbHvvp.setContainerDataSource(HanhViViPham.class, DAOs.get(HanhViViPhamDAO.class)
							.getListByNhomHv(this.nhomhvBCTH, this.nhomhvKhacBCTH, 0, nghiDinh));
				}
			}
		} else if (this.cmbNhomHv.isEmpty() == true) {
			this.nhomhvBCTH = "";
			this.nhomhvKhacBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbLoaiPt}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbLoaiPt_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbLoaiPt.getSelectedItem() != null) {
			long nghiDinh = 0;
			if (this.cmbNghiDinh.getSelectedItem() != null) {
				nghiDinh = this.cmbNghiDinh.getSelectedItem().getBean().getId();
			}
			long idNhom = 0;
			if (this.cmbnhomHvvp.getSelectedItem() != null) {
				idNhom = this.cmbnhomHvvp.getSelectedItem().getBean().getId();
			}
			this.cmbHvvp.setContainerDataSource(HanhViViPham.class,
					DAOs.get(HanhViViPhamDAO.class).getListByNhomHv(this.nhomhvBCTH, this.nhomhvKhacBCTH,
							this.cmbLoaiPt.getSelectedItem().getBean().getId(), nghiDinh));
			// this.cmbHvvp.setContainerDataSource(HanhViViPham.class,
			// DAOs.get(HanhViViPhamDAO.class).listByNhomHv(idNhom,
			// nghiDinh, this.cmbLoaiPt.getSelectedItem().getBean().getId()));
			this.loaiPhuongTienBCTH = this.cmbLoaiPt.getSelectedItem().getBean().getLoaiPt();
		} else {
			this.loaiPhuongTienBCTH = "";
		}
		// this.cmbHvvp.setContainerDataSource(HanhViViPham.class,
		// DAOs.get(HanhViViPhamDAO.class).getListByNhomHv(
		// this.nhomhv, this.nhomhvKhac,
		// this.cmbLoaiPt.getSelectedItem().getBean().getId(), nghiDinh));
		// }
	}

	/**
	 * Event handler delegate method for the {@link XdevPopupDateField}
	 * {@link #pdFTuocTuNgay}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void pdFTuocTuNgay_valueChange(final Property.ValueChangeEvent event) {
		if (this.pdFTuocTuNgay.isEmpty() == false) {
			final Date fromDate = this.pdFTuocTuNgay.getValue();
			final DateFormat dfm1 = new SimpleDateFormat("dd/MM/yyyy");
			this.tuocTuNgayBCTH = dfm1.format(fromDate);
		} else {
			this.tuocTuNgayBCTH = "";
		}

	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbNghiDinh}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbNghiDinh_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbNghiDinh.getSelectedItem() != null) {
			final long idNghiDinh = this.cmbNghiDinh.getSelectedItem().getBean().getId();
			long loaiPt = 0;
			if (this.cmbLoaiPt.getSelectedItem() != null) {
				loaiPt = this.cmbLoaiPt.getSelectedItem().getBean().getId();
			}
			long idNhom = 0;
			if (this.cmbnhomHvvp.getSelectedItem() != null) {
				idNhom = this.cmbnhomHvvp.getSelectedItem().getBean().getId();
			}
			this.cmbHvvp.setContainerDataSource(HanhViViPham.class,
					DAOs.get(HanhViViPhamDAO.class).listByNhomHv(idNhom, idNghiDinh, loaiPt));
			this.nghiDinhBCTH = this.cmbNghiDinh.getSelectedItem().getBean().getMa();
		} else {
			this.nghiDinhBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbnhomHvvp}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbnhomHvvp_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbnhomHvvp.getSelectedItem() != null) {
			final long idNhom = this.cmbnhomHvvp.getSelectedItem().getBean().getId();
			long idNghiDinh = 0;
			if (this.cmbNghiDinh.getSelectedItem() != null) {
				idNghiDinh = this.cmbNghiDinh.getSelectedItem().getBean().getId();
			}
			long idLoaiPt = 0;
			if (this.cmbLoaiPt.getSelectedItem() != null) {
				idLoaiPt = this.cmbLoaiPt.getSelectedItem().getBean().getId();
			}
			this.cmbHvvp.setContainerDataSource(HanhViViPham.class,
					DAOs.get(HanhViViPhamDAO.class).listByNhomHv(idNhom, idNghiDinh, idLoaiPt));
			final List<HanhViViPham> listLuatTc = DAOs.get(HanhViViPhamDAO.class).listByNhomHv(idNhom, idNghiDinh,
					idLoaiPt);
			if (listLuatTc.size() > 0) {
				for (int i = 0; i < listLuatTc.size(); i++) {
					this.resourceHv.add(listLuatTc.get(i).getId());
					this.nhomhvBCTH = Bao_Cao_Tong_Hop.this.resourceHv.toString().substring(1,
							this.resourceHv.toString().length() - 1);

				}
			} else {
				this.nhomhvBCTH = "";
			}
		} else {
			this.nhomhvBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbLoaiGiayTo}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbLoaiGiayTo_valueChange(final Property.ValueChangeEvent event) {
		long kieuGiayTo = 0;
		if (this.cmbLoaiGiayTo.getValue() != null && this.cmbLoaiGiayTo.getValue() != "") {
			if (this.cmbLoaiGiayTo.getValue() == "Tất cả") {
				this.tangVatBCTH = "";
			} else {
				if (this.cmbLoaiGiayTo.getValue() == "GPLX") {
					kieuGiayTo = 1;
					this.tangVatBCTH = "1";
				} else if (this.cmbLoaiGiayTo.getValue() == "Đăng ký") {
					kieuGiayTo = 3;
					this.tangVatBCTH = "3";
				} else if (this.cmbLoaiGiayTo.getValue() == "Kiểm định") {
					this.tangVatBCTH = "2";
				} else if (this.cmbLoaiGiayTo.getValue() == "Phương tiện") {
					this.tangVatBCTH = "4";
				} else if (this.cmbLoaiGiayTo.getValue() == "Khác") {
					this.tangVatBCTH = "5";
				}
			}
			this.cmbNoiCapTv.setContainerDataSource(NoicapGiayto.class,
					DAOs.get(NoicapGiaytoDAO.class).GetQdById(kieuGiayTo));

		} else {
			this.cmbNoiCapTv.setContainerDataSource(NoicapGiayto.class,
					DAOs.get(NoicapGiaytoDAO.class).GetQdById(kieuGiayTo));
			this.tangVatBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbLoaiBb}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbLoaiBb_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbLoaiBb.getValue() != null && this.cmbLoaiBb.getValue() != "") {
			if (this.cmbLoaiBb.getValue() == "QĐ XPVPHCKLBB (01)") {
				this.loaiBBBCTH = 1;
			} else if (this.cmbLoaiBb.getValue() == "BB VPHC (43)") {
				this.loaiBBBCTH = 43;
			} else if (this.cmbLoaiBb.getValue() == "QĐ XPVPHC (02)") {
				this.loaiBBBCTH = 2;
			} else if (this.cmbLoaiBb.getValue() == "QĐ TGTV (18)") {
				this.loaiBBBCTH = 18;
			} else if (this.cmbLoaiBb.getValue() == "QĐ TLTV (20)") {
				this.loaiBBBCTH = 20;
			} else if (this.cmbLoaiBb.getValue() == "BB TGTV (50)") {
				this.loaiBBBCTH = 50;
			} else if (this.cmbLoaiBb.getValue() == "BB TLTV (60)") {
				this.loaiBBBCTH = 60;
			} else if (this.cmbLoaiBb.getValue() == "Tất cả") {
				this.loaiBBBCTH = 0;
			}
		} else if (this.cmbLoaiBb.getValue() == null) {
			this.loaiBBBCTH = 0;
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbTinh}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbTinh_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbTinh.getSelectedItem() != null) {
			this.TinhBCTH = this.cmbTinh.getSelectedItem().getBean().getTen().toUpperCase();
			this.cmbQuanHuyen.setContainerDataSource(DiaDanhHanhChinh.class, DAOs.get(DiaDanhHanhChinhDAO.class)
					.getListCapTren(this.cmbTinh.getSelectedItem().getBean().getId(), "2"));
			this.cmbPhuongXa.clear();
			this.diaBanVp = this.cmbTinh.getSelectedItem().getBean().getTen();
			this.cmbQuocLo.setContainerDataSource(QuocLoTuyenduong.class,
					DAOs.get(QuocLoTuyenduongDAO.class).getListByIdDvAndDD(
							Authentication.getAuthUser().getDonViCanhsatGt().getId(), 1,
							this.cmbTinh.getSelectedItem().getBean().getId()));
			this.cmbTuyenDuong.setContainerDataSource(QuocLoTuyenduong.class,
					DAOs.get(QuocLoTuyenduongDAO.class).getListByIdDvAndDD(
							Authentication.getAuthUser().getDonViCanhsatGt().getId(), 2,
							this.cmbTinh.getSelectedItem().getBean().getId()));
		} else {
			this.cmbQuanHuyen.setContainerDataSource(DiaDanhHanhChinh.class,
					DAOs.get(DiaDanhHanhChinhDAO.class).getListCapTren(0, "2"));
			this.cmbPhuongXa.clear();
			this.TinhBCTH = "";
			this.diaBanVp = "";
			if (Authentication.getAuthUser().getDonViCanhsatGt().getDonViResourceId() == 1) {
				this.cmbQuocLo.setContainerDataSource(QuocLoTuyenduong.class,
						DAOs.get(QuocLoTuyenduongDAO.class).ListPoolByIdQuocLo());
				this.cmbTuyenDuong.setContainerDataSource(QuocLoTuyenduong.class,
						DAOs.get(QuocLoTuyenduongDAO.class).ListPoolByIdTuyenduong());
			} else {
				this.cmbQuocLo.setContainerDataSource(QuocLoTuyenduong.class, DAOs.get(QuocLoTuyenduongDAO.class)
						.getListByIdDv(Authentication.getAuthUser().getDonViCanhsatGt().getId(), 1));
				this.cmbTuyenDuong.setContainerDataSource(QuocLoTuyenduong.class, DAOs.get(QuocLoTuyenduongDAO.class)
						.getListByIdDv(Authentication.getAuthUser().getDonViCanhsatGt().getId(), 2));
			}
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbQuanHuyen}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbQuanHuyen_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbQuanHuyen.getSelectedItem() != null) {
			this.cmbPhuongXa.setContainerDataSource(DiaDanhHanhChinh.class, DAOs.get(DiaDanhHanhChinhDAO.class)
					.getListCapDuoi(this.cmbQuanHuyen.getSelectedItem().getBean().getId(), "3"));
			this.QuanBCTH = this.cmbQuanHuyen.getSelectedItem().getBean().getTen().toUpperCase();
			this.diaBanVp = this.cmbQuanHuyen.getSelectedItem().getBean().getTen() + ", "
					+ this.cmbTinh.getSelectedItem().getBean().getTen();
		} else {
			this.cmbPhuongXa.setContainerDataSource(DiaDanhHanhChinh.class,
					DAOs.get(DiaDanhHanhChinhDAO.class).getListCapDuoi(0, "3"));
			this.QuanBCTH = "";
			if (this.cmbTinh.getSelectedItem() != null) {
				this.diaBanVp = this.cmbTinh.getSelectedItem().getBean().getTen();
			} else {
				this.diaBanVp = "";
			}
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevTextField}
	 * {@link #txtTenNvp}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void txtTenNvp_valueChange(final Property.ValueChangeEvent event) {
		if (this.txtTenNvp.isEmpty() == false) {
			this.tenNvpBCTH = this.txtTenNvp.getValue().trim();
		} else {
			this.tenNvpBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevTextField}
	 * {@link #txtSoBb}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void txtSoBb_valueChange(final Property.ValueChangeEvent event) {
		if (this.txtSoBb.isEmpty() == false) {
			this.soBBBCTH = this.txtSoBb.getValue().trim();
		} else {
			this.soBBBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevTextField}
	 * {@link #txtDiaChiNvp}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void txtDiaChiNvp_valueChange(final Property.ValueChangeEvent event) {
		if (this.txtDiaChiNvp.isEmpty() == false) {
			this.DiaChiNvpBCTH = this.txtDiaChiNvp.getValue().trim();
		} else {
			this.DiaChiNvpBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevTextField}
	 * {@link #txtSoGiayTo}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void txtSoGiayTo_valueChange(final Property.ValueChangeEvent event) {
		if (this.txtSoGiayTo.isEmpty() == false) {
			this.SoGiayToBCTH = this.txtSoGiayTo.getValue().trim();
		} else {
			this.SoGiayToBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbNoiCapTv}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbNoiCapTv_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbNoiCapTv.getSelectedItem() != null) {
			this.NoiCapTvBCTH = this.cmbNoiCapTv.getSelectedItem().getBean().getTen().trim();
		} else {
			this.NoiCapTvBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbHangGplx}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbHangGplx_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbHangGplx.getSelectedItem() != null) {
			this.HangGPLXBCTH = this.cmbHangGplx.getSelectedItem().getBean().getTenHang().trim();
		} else {
			this.HangGPLXBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbCanBoLap}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbCanBoLap_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbCanBoLap.getSelectedItem() != null) {
			this.CanBoBCTH = this.cmbCanBoLap.getSelectedItem().getBean().getUserName().trim();
		} else {
			this.CanBoBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevTextField}
	 * {@link #txtTuoiTu}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void txtTuoiTu_valueChange(final Property.ValueChangeEvent event) {
		if (this.txtTuoiTu.isEmpty() == false) {
			final String pattern = "[0-9]{0,99999999999999999999}";
			final String sotuoi = this.txtTuoiTu.getValue().trim();
			if (Pattern.matches(pattern, sotuoi) == false && this.txtTuoiTu.getValue().length() > 0) {
				Notification.show("Không đúng định dạng");
				this.txtTuoiTu.clear();
				return;
			}
			this.tuoiBCTH = Long.parseLong(this.txtTuoiTu.getValue().trim());
			final long thisYear = new Date().getYear() + 1900;
			if (this.txtTuoiDen.isEmpty() == true) {
				this.txtNamSinhDen.setValue(String.valueOf(thisYear - this.tuoiBCTH));
				this.txtNamSinhDen.setEnabled(false);
			} else if (this.txtTuoiDen.isEmpty() == false) {
				this.txtNamSinhDen.setValue(String.valueOf(thisYear - this.tuoiBCTH));
				this.txtNamSinhDen.setEnabled(false);
				this.txtNamSinhTu.setValue(String.valueOf(thisYear - this.tuoiDenBCTH));
				this.txtNamSinhTu.setEnabled(false);
			}
		} else {
			this.tuoiBCTH = 0;
			this.txtNamSinhTu.setValue("");
			this.txtNamSinhTu.setEnabled(true);
			this.txtNamSinhDen.setValue("");
			this.txtNamSinhDen.setEnabled(true);
			if (this.txtTuoiDen.isEmpty() == false) {
				final long thisYear = new Date().getYear() + 1900;
				this.txtNamSinhTu.setValue(String.valueOf(thisYear - this.tuoiDenBCTH));
				this.txtNamSinhTu.setEnabled(false);
			}
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevTextField}
	 * {@link #txtTuoiDen}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void txtTuoiDen_valueChange(final Property.ValueChangeEvent event) {
		if (this.txtTuoiDen.isEmpty() == false) {
			final String pattern = "[0-9]{0,99999999999999999999}";
			final String sotuoi = this.txtTuoiTu.getValue().trim();
			if (Pattern.matches(pattern, sotuoi) == false && this.txtTuoiTu.getValue().length() > 0) {
				Notification.show("Không đúng định dạng");
				this.txtTuoiTu.clear();
				return;
			}
			this.tuoiDenBCTH = Long.parseLong(this.txtTuoiDen.getValue().trim());
			final long thisYear = new Date().getYear() + 1900;
			if (this.txtTuoiTu.isEmpty() == true) {
				this.txtNamSinhTu.setValue(String.valueOf(thisYear - this.tuoiDenBCTH));
				this.txtNamSinhTu.setEnabled(false);
			} else if (this.txtTuoiTu.isEmpty() == false) {
				this.txtNamSinhTu.setValue(String.valueOf(thisYear - this.tuoiDenBCTH));
				this.txtNamSinhTu.setEnabled(false);
				this.txtNamSinhDen.setValue(String.valueOf(thisYear - this.tuoiBCTH));
				this.txtNamSinhDen.setEnabled(false);
			}
		} else {
			this.tuoiDenBCTH = 0;
			this.txtNamSinhTu.setValue("");
			this.txtNamSinhTu.setEnabled(true);
			this.txtNamSinhDen.setValue("");
			this.txtNamSinhDen.setEnabled(true);
			if (this.txtTuoiTu.isEmpty() == false) {
				final long thisYear = new Date().getYear() + 1900;
				this.txtNamSinhDen.setValue(String.valueOf(thisYear - this.tuoiBCTH));
				this.txtNamSinhDen.setEnabled(false);
			}
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevPopupDateField}
	 * {@link #dateNgayTamGiuTu}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void dateNgayTamGiuTu_valueChange(final Property.ValueChangeEvent event) {
		if (this.dateNgayTamGiuTu.isEmpty() == false) {
			String dateTg = "";
			if (this.dateNgayTamGiuTu.getValue().getDate() < 10) {
				dateTg = "0" + this.dateNgayTamGiuTu.getValue().getDate();
			} else if (this.dateNgayTamGiuTu.getValue().getDate() >= 10) {
				dateTg = String.valueOf(this.dateNgayTamGiuTu.getValue().getDate());
			}
			String MonthTg = "";
			if ((this.dateNgayTamGiuTu.getValue().getMonth() + 1) < 10) {
				MonthTg = "0" + String.valueOf((this.dateNgayTamGiuTu.getValue().getMonth() + 1));
			} else if ((this.dateNgayTamGiuTu.getValue().getMonth() + 1) > 10) {
				MonthTg = String.valueOf(this.dateNgayTamGiuTu.getValue().getMonth() + 1);
			}
			this.thoiHanTuBCTH = String.valueOf(this.dateNgayTamGiuTu.getValue().getYear() + 1900).substring(2)
					+ MonthTg + dateTg;
		} else {
			this.thoiHanTuBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevPopupDateField}
	 * {@link #dateNgayTamGiuDen}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void dateNgayTamGiuDen_valueChange(final Property.ValueChangeEvent event) {
		if (this.dateNgayTamGiuDen.isEmpty() == false) {
			String dateTg1 = "";
			if (this.dateNgayTamGiuDen.getValue().getDate() < 10) {
				dateTg1 = "0" + this.dateNgayTamGiuDen.getValue().getDate();
			} else if (this.dateNgayTamGiuDen.getValue().getDate() >= 10) {
				dateTg1 = String.valueOf(this.dateNgayTamGiuDen.getValue().getDate());
			}
			String MonthTg = "";
			if ((this.dateNgayTamGiuDen.getValue().getMonth() + 1) < 10) {
				MonthTg = "0" + String.valueOf((this.dateNgayTamGiuDen.getValue().getMonth() + 1));
			} else if ((this.dateNgayTamGiuDen.getValue().getMonth() + 1) > 10) {
				MonthTg = String.valueOf(this.dateNgayTamGiuDen.getValue().getMonth() + 1);
			}
			this.thoiHanDenBCTH = String.valueOf(this.dateNgayTamGiuDen.getValue().getYear() + 1900).substring(2)
					+ MonthTg + dateTg1;
		} else {
			this.thoiHanDenBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevTextField}
	 * {@link #txtBKS}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void txtBKS_valueChange(final Property.ValueChangeEvent event) {
		if (this.txtBKS.isEmpty() == false) {
			this.BKSBCTH = this.txtBKS.getValue().trim();
		} else {
			this.BKSBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevPopupDateField}
	 * {@link #FromDate}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void FromDate_valueChange(final Property.ValueChangeEvent event) {
		if (this.FromDate.isEmpty() == false) {
			if (this.FromDate.isEmpty() == false) {
				final Date fromDate = this.FromDate.getValue();
				final DateFormat dfm1 = new SimpleDateFormat("dd-MMM-yy");
				final DateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");
				this.tuNgayBCTH = dfm.format(fromDate);
				this.fromdateBCTH = dfm1.format(fromDate);
			} else {
				this.tuNgayBCTH = "";
				this.fromdateBCTH = "";
			}
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbLinhVuc}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbLinhVuc_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbLinhVuc.getValue() != null && this.cmbLinhVuc.getValue() != "") {
			if (this.cmbLinhVuc.getValue() == "Tất cả") {
				this.LinhVucBCTH = 0;
			} else if (this.cmbLinhVuc.getValue() == "Đường bộ") {
				this.LinhVucBCTH = 1;
			} else if (this.cmbLinhVuc.getValue() == "Đường sắt") {
				this.LinhVucBCTH = 2;
			} else if (this.cmbLinhVuc.getValue() == "Đường thủy") {
				this.LinhVucBCTH = 4;
			}
		} else {
			this.LinhVucBCTH = 0;
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevPopupDateField}
	 * {@link #ToDate}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void ToDate_valueChange(final Property.ValueChangeEvent event) {
		if (this.ToDate.isEmpty() == false) {
			if (this.ToDate.isEmpty() == false) {
				final Date fromDate = this.ToDate.getValue();
				final DateFormat dfm1 = new SimpleDateFormat("dd/MM/yyyy");
				final DateFormat dfm = new SimpleDateFormat("dd-MMM-yy");
				this.denNgayBCTH = dfm1.format(fromDate);
				this.todateBCTH = dfm.format(fromDate);
			} else {
				this.denNgayBCTH = "";
				this.todateBCTH = "";
			}
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbToChuc}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbToChuc_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbToChuc.getValue() != null && this.cmbToChuc.getValue() != "") {
			if (this.cmbToChuc.getValue() == "Tất cả") {
				this.ToChucBCTH = -1;
			} else if (this.cmbToChuc.getValue() == "Cá nhân") {
				this.ToChucBCTH = 0;
			} else if (this.cmbToChuc.getValue() == "Tổ chức") {
				this.ToChucBCTH = 1;
			}
		} else {
			this.ToChucBCTH = -1;
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbNgheNghiep}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbNgheNghiep_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbNgheNghiep.getSelectedItem() != null) {
			this.ngheNghiepNvpBCTH = this.cmbNgheNghiep.getSelectedItem().getBean().getTenNgheNghiep().trim();
		} else {
			this.ngheNghiepNvpBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevTextField}
	 * {@link #txtNamSinhTu}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void txtNamSinhTu_valueChange(final Property.ValueChangeEvent event) {
		if (this.txtNamSinhTu.isEmpty() == false) {
			this.tuNamBCTH = this.txtNamSinhTu.getValue().toString().trim();
		} else {
			this.tuNamBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevTextField}
	 * {@link #txtNamSinhDen}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void txtNamSinhDen_valueChange(final Property.ValueChangeEvent event) {
		if (this.txtNamSinhDen.isEmpty() == false) {
			this.denNamBCTH = this.txtNamSinhDen.getValue().toString().trim();
		} else {
			this.denNamBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbCapPheDuyet}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbCapPheDuyet_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbCapPheDuyet.getSelectedItem() != null) {
			this.chucVuBCTH = this.cmbCapPheDuyet.getSelectedItem().getBean().getTenChucVu().trim().toUpperCase();
		} else {
			this.chucVuBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevPopupDateField}
	 * {@link #pdFTuocDenNgay}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void pdFTuocDenNgay_valueChange(final Property.ValueChangeEvent event) {
		if (this.pdFTuocDenNgay.isEmpty() == false) {
			if (this.pdFTuocDenNgay.isEmpty() == false) {
				final Date fromDate = this.pdFTuocDenNgay.getValue();
				final DateFormat dfm1 = new SimpleDateFormat("dd/MM/yyyy");
				this.tuocDenNgayBCTH = dfm1.format(fromDate);
			} else {
				this.tuocDenNgayBCTH = "";
			}
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbHinhThucPhat}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbHinhThucPhat_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbHinhThucPhat.getSelectedItem() != null) {
			this.hinhThucPhatBCTH = this.cmbHinhThucPhat.getSelectedItem().getBean().getTenHinhThuc().trim();
		} else {
			this.hinhThucPhatBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevTextField}
	 * {@link #txtPhatTienTu}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void txtPhatTienTu_valueChange(final Property.ValueChangeEvent event) {
		if (this.txtPhatTienTu.isEmpty() == false) {
			if (!this.txtPhatTienTu.getValue().contains(".")) {
				final NumberFormat fmMoney = NumberFormat.getCurrencyInstance(new Locale("vn", "VN"));
				final DecimalFormatSymbols formatSym = ((DecimalFormat) fmMoney).getDecimalFormatSymbols();
				formatSym.setCurrencySymbol("");
				((DecimalFormat) fmMoney).setDecimalFormatSymbols(formatSym);
				this.txtPhatTienTu.setValue(
						fmMoney.format(Long.parseLong(String.valueOf(this.txtPhatTienTu.getValue().toString())))
								.replace(",", ".").trim());
			}
			if (this.txtPhatTienTu.getValue().contains(".")) {
				this.TienPhatTuBCTH = this.txtPhatTienTu.getValue().replace(".", "").trim();
			} else {
				this.TienPhatTuBCTH = this.txtPhatTienTu.getValue().trim();
			}
		} else {
			this.TienPhatTuBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevTextField}
	 * {@link #txtPhatTienDen}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void txtPhatTienDen_valueChange(final Property.ValueChangeEvent event) {
		if (this.txtPhatTienDen.isEmpty() == false) {
			if (!this.txtPhatTienDen.getValue().contains(".")) {
				final NumberFormat fmMoney = NumberFormat.getCurrencyInstance(new Locale("vn", "VN"));
				final DecimalFormatSymbols formatSym = ((DecimalFormat) fmMoney).getDecimalFormatSymbols();
				formatSym.setCurrencySymbol("");
				((DecimalFormat) fmMoney).setDecimalFormatSymbols(formatSym);
				this.txtPhatTienDen.setValue(
						fmMoney.format(Long.parseLong(String.valueOf(this.txtPhatTienDen.getValue().toString())))
								.replace(",", ".").trim());
			}
			if (this.txtPhatTienDen.getValue().contains(".")) {
				this.TienPhatDenBCTH = this.txtPhatTienDen.getValue().replace(".", "").trim();
			} else {
				this.TienPhatDenBCTH = this.txtPhatTienDen.getValue().trim();
			}
		} else {
			this.TienPhatDenBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbHinhThucNP}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbHinhThucNP_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbHinhThucNP.getValue() != "" && this.cmbHinhThucNP.getValue() != null) {
			if (this.cmbHinhThucNP.getValue() == "Tất cả") {
				this.HinhThucNopPhatBCTH = "";
			} else if (this.cmbHinhThucNP.getValue() == "Qua DVC") {
				this.HinhThucNopPhatBCTH = "3, 4";
			} else if (this.cmbHinhThucNP.getValue() == "Trực tiếp") {
				this.HinhThucNopPhatBCTH = "5";
			}
		} else {
			this.HinhThucNopPhatBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbHvvp}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbHvvp_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbHvvp.getSelectedItem() != null) {
			if (this.cmbLoaiPt.getSelectedItem() != null
					&& (this.cmbNhomHv.getValue() != null && this.cmbNhomHv.getValue() != "")) {
				this.hanhViVPBCTH = this.cmbHvvp.getSelectedItem().getBean().getId();
				this.checkHvvp = 1;

			} else if (this.cmbLoaiPt.getSelectedItem() == null
					&& (this.cmbNhomHv.getValue() != null && this.cmbNhomHv.getValue() != "")) {
				if (!this.cmbNhomHv.getValue().toString().contains("Tất cả")) {
					this.hanhViVPBCTH = this.cmbHvvp.getSelectedItem().getBean().getId();
					this.checkHvvp = 1;
				} else {
					this.hanhViVPBCTH = this.cmbHvvp.getSelectedItem().getBean().getLuatId();
					this.checkHvvp = 0;
				}
			} else if (this.cmbLoaiPt.getSelectedItem() == null
					&& (this.cmbNhomHv.getValue() == null || this.cmbNhomHv.getValue() == "")) {
				this.hanhViVPBCTH = this.cmbHvvp.getSelectedItem().getBean().getLuatId();
				this.checkHvvp = 0;

			} else if (this.cmbLoaiPt.getSelectedItem() != null
					&& (this.cmbNhomHv.getValue() == null || this.cmbNhomHv.getValue() == "")) {

				this.hanhViVPBCTH = this.cmbHvvp.getSelectedItem().getBean().getId();
				this.checkHvvp = 1;

			}
		} else {
			this.hanhViVPBCTH = 0;
			this.checkHvvp = 0;
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbNopTrucTuyenQua}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbNopTrucTuyenQua_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbNopTrucTuyenQua.getSelectedItem() != null) {
			this.khobacBCTH = this.cmbNopTrucTuyenQua.getSelectedItem().getBean().getTen();
		} else {
			this.khobacBCTH = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbTrangThaiXl}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbTrangThaiXl_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbTrangThaiXl.getValue() != null && this.cmbTrangThaiXl.getValue() != "") {
			if (this.cmbTrangThaiXl.getValue() == "Tất cả") {
				this.TrangThaiXuLyBCTH = -1;
			} else if (this.cmbTrangThaiXl.getValue() != "Tất cả") {
				if (this.cmbTrangThaiXl.getValue() == "Đang tạm giữ") {
					this.TrangThaiXuLyBCTH = 0;
				} else if (this.cmbTrangThaiXl.getValue() == "Đã trả") {
					this.TrangThaiXuLyBCTH = 1;
				}
			}
		} else {
			this.TrangThaiXuLyBCTH = -1;
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbPhuongXa}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbPhuongXa_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbPhuongXa.getSelectedItem() != null) {
			this.XaBCTH = this.cmbPhuongXa.getSelectedItem().getBean().getTen().toUpperCase();
			this.diaBanVp = this.cmbPhuongXa.getSelectedItem().getBean().getTen() + ", "
					+ this.cmbQuanHuyen.getSelectedItem().getBean().getTen() + ", "
					+ this.cmbTinh.getSelectedItem().getBean().getTen();
		} else {
			this.XaBCTH = "";
			if (this.cmbTinh.getSelectedItem() != null && this.cmbQuanHuyen.getSelectedItem() == null) {
				this.diaBanVp = this.cmbTinh.getSelectedItem().getBean().getTen();
			} else if (this.cmbTinh.getSelectedItem() != null && this.cmbQuanHuyen.getSelectedItem() != null) {
				this.diaBanVp = this.cmbQuanHuyen.getSelectedItem().getBean().getTen() + ", "
						+ this.cmbTinh.getSelectedItem().getBean().getTen();
			} else if (this.cmbTinh.getSelectedItem() != null && this.cmbQuanHuyen.getSelectedItem() != null) {
				this.diaBanVp = "";
			}
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbQuocLo}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbQuocLo_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbQuocLo.getSelectedItem() != null) {
			this.QuocLoBCTH = this.cmbQuocLo.getSelectedItem().getBean().getTenDuong().toUpperCase();
			this.diaBanVp = this.cmbQuocLo.getSelectedItem().getBean().getTenDuong();
		} else {
			this.QuocLoBCTH = "";
			this.diaBanVp = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbTuyenDuong}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbTuyenDuong_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbTuyenDuong.getSelectedItem() != null) {
			this.TuyenDuongBCTH = this.cmbTuyenDuong.getSelectedItem().getBean().getTenDuong().toUpperCase();
			this.diaBanVp = this.cmbTuyenDuong.getSelectedItem().getBean().getTenDuong();
		} else {
			this.TuyenDuongBCTH = "";
			this.diaBanVp = "";
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevComboBox}
	 * {@link #cmbXpbs}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbXpbs_valueChange(final Property.ValueChangeEvent event) {
		if (this.cmbXpbs.getSelectedItem() != null) {
			this.xpbsBCTH = this.cmbXpbs.getSelectedItem().getBean().getGhiChu();
		} else {
			this.xpbsBCTH = "";
		}
	}

	private void xlsxExport(final String loaiBaoCao) {
		// try {
		final BaoCaoTongHopDAO dao = new BaoCaoTongHopDAO();
		// final ArrayList<BaoCaoTongHop> listBaoCao1 = new ArrayList<>();
		ArrayList<BaoCaoTongHop> listBaoCao = new ArrayList<>();
		listBaoCao = dao.WeeklyReport(this.danhSachBCTH);

		/*
		 * for ( final BaoCaoTongHop vuviec43 : listBaoCao){
		 * System.out.println(vuviec43.getMaVuViec());
		 * 
		 * }
		 */
		/*
		 * for( final BaoCaoTongHop vuviec42 : listBaoCao1){
		 * System.out.println(listBaoCao1); listBaoCao1.add(vuviec42); }
		 */

		/*
		 * if (vuviec43.getLoaiBbQd() == 43) {
		 * C5.setCellValue("Biên bản vi phạm hành chính"); } else if
		 * (vuviec43.getLoaiBbQd() == 2) {
		 * C5.setCellValue("Quyết định xử phạt vi phạm hành chính"); } else if
		 * (vuviec43.getLoaiBbQd() == 1) { C5.
		 * setCellValue("Quyết định xử phạt vi phạm hành chính không lập biên bản"
		 * ); } else if (vuviec43.getLoaiBbQd() == 18) {
		 * C5.setCellValue("Quyết định tạm giữ tang vật phương tiện"); } else if
		 * (vuviec43.getLoaiBbQd() == 20) {
		 * C5.setCellValue("Quyết định trả lại tang vật phương tiện"); } else if
		 * (vuviec43.getLoaiBbQd() == 60) {
		 * C5.setCellValue("Biên bản trả lại tang vật phương tiện"); } else if
		 * (vuviec43.getLoaiBbQd() == 50) {
		 * C5.setCellValue("Biên bản tạm giữ tang vật phương tiện"); }
		 */

		// this.table.setContainerDataSource(BaoCaoTongHop.class,
		// DAOs.get(BaoCaoTongHopDAO.class).WeeklyReport(this.danhSachBCTH));
		this.table.setContainerDataSource(BaoCaoTongHop.class, listBaoCao);
		/*
		 * for(final BaoCaoTongHop listvuviec : listBaoCao){ final BaoCaoTongHop
		 * baoCao = new BaoCaoTongHop(); if(listvuviec.getLoaiBbQd() == 43){
		 * listvuviec.setTenBBQd("Biên bản vi phạm hành chính"); } else
		 * if(listvuviec.getLoaiBbQd() == 2){
		 * listvuviec.setTenBBQd("Quyết định xử phạt vi phạm hành chính"); }
		 * else if(listvuviec.getLoaiBbQd() == 1){ listvuviec.
		 * setTenBBQd("Quyết định xử phạt vi phạm hành chính không lập biên bản"
		 * ); } }
		 */
		this.table.setVisibleColumns(BaoCaoTongHop_.uuid.getName(), BaoCaoTongHop_.maVuViec.getName(),
				BaoCaoTongHop_.maRutgon.getName(), "tenBBQd", BaoCaoTongHop_.soBienBan.getName(),
				BaoCaoTongHop_.tenNguoiNvp.getName());

		// final Report report = Report.New();
		// final String reportSrc =
		// "WebContent/WEB-INF/resources/BaoCaoTongHop2.jrxml";
		// report.jrxml(reportSrc);
		//
		// report.dataSource(listBaoCao).parameter("loaiBaoCao", loaiBaoCao)
		// .parameter("data", new JRBeanCollectionDataSource(listBaoCao));
		// final Resource resource = report.exportToResource(ExportType.XLSX);
		//// Page.getCurrent().open(resource, "_blank", false);
		// this.browserFrame2.setSource(resource);

		// } catch (final Exception e) {
		// // this.e.printStackTrace();
		// }
	}

	/**
	 * Event handler delegate method for the {@link XdevButton}
	 * {@link #btnWeeklyReport}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnWeeklyReport_buttonClick(final Button.ClickEvent event) {String tuan = "";
	final List<Long> list = new ArrayList<>(this.resourceSet);
	if (!this.cmbThang.isEmpty()) {
		tuan = this.cmbTuan.getValue().toString().trim();
	} else {
		Notification.show("VUI LÒNG CHỌN TUẦN XUẤT BÁO CÁO");
		this.cmbThang.focus();
		return;
	}

	final String ngayBatDauDayDu = tuan.substring(0, tuan.indexOf(" ")).trim();
	final String ngayBatDau = ngayBatDauDayDu.substring(0, 2);
	final String ngayKetThucDayDu = tuan.substring(tuan.lastIndexOf(" ")).trim();
	final String nam = ngayBatDauDayDu.substring(6);
	String thang = ngayBatDauDayDu.substring(3, 5);
	if (thang.length() == 1) {
		thang = "0" + thang;
	}

	String temporaryDirectory = "";
	if (SystemUtils.IS_OS_LINUX) {
		temporaryDirectory = System.getenv("REPORTING_PATH");
		temporaryDirectory = temporaryDirectory + "/weekly/"+nam+"/"+thang+"/"+ngayBatDau+"/";
		System.out.print(temporaryDirectory);
	} else {
		temporaryDirectory = "D:/";
	}

	if (this.checkBox.getValue() && Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId() == 1) {
		File source = new File("");
		final String NameBCW = "BCTH_0_"+ngayBatDauDayDu.replace("/", "")+"_"+ngayKetThucDayDu.replace("/", "");;
		final String ReNameBCW = "BCTH_C08_"+ngayBatDauDayDu.replace("/", "")+"_"+ngayKetThucDayDu.replace("/", "");
		
		source = new File(temporaryDirectory + NameBCW+".xlsx");
				// File (or directory) with new name
//		final File fileRename = new File(temporaryDirectory + ReNameBCW+".xlsx");
//			source = new File("//10.0.3.237/up/reporting/weekly/" + nam + "/" + thang +"/"+ ngayBatDau + "/"+NameBCW+ ".xlsx");
//			final File fileRename = new File("//10.0.3.237/up/reporting/weekly/" + nam + "/" + thang +"/"+ ngayBatDau + "/"+ReNameBCW+ ".xlsx");
		final String path = source.getPath();
		LayFileExcel(path, ReNameBCW);
	}
	else
	{
		File source = new File("");
		for (final Long long1 : list) {
			final String NameBCW = ("BCTH_"+long1+"_"+ngayBatDauDayDu.replace("/", "")+"_"+ngayKetThucDayDu.replace("/", "")).replace("/","");
			final DonViCanhsatGt dv = new DonViCanhsatGtDAO().find(long1);
			final String maTrucThuoc = dv.getMaTrucThuoc();
			final String tenBC = dv.getTenVietTatDonViBc();
			final String ddhc  = dv.getDiaDanhHanhChinh().getTenVietTat();
			String ReNameBCW = "";
			if(maTrucThuoc.equals("G01")){
				
				ReNameBCW = ("BCTH_C08_"+tenBC+"_"+ngayBatDauDayDu+"_"+ngayKetThucDayDu).replace("/","");
				}else{
					
				ReNameBCW = ("BCTH_PC08_"+ddhc+"_"+tenBC+"_"+ngayBatDauDayDu+"_"+ngayKetThucDayDu).replace("/","");
				}
			
			source = new File(temporaryDirectory + NameBCW+".xlsx");
					// File (or directory) with new name
			final File fileRename = new File(temporaryDirectory + ReNameBCW+".xlsx");
//			source = new File("//10.0.3.237/up/reporting/weekly/" + nam + "/" + thang +"/"+ ngayBatDau + "/"+NameBCW+".xlsx");
//			final File fileRename = new File("//10.0.3.237/up/reporting/weekly/" + nam + "/" + thang +"/"+ ngayBatDau + "/"+ReNameBCW+".xlsx");
			final String path = source.getPath();
			LayFileExcel(path, ReNameBCW);
	}
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevButton}
	 * {@link #btnMonthlyReport}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnMonthlyReport_buttonClick(final Button.ClickEvent event) {
		String nam = "";
		String thang1 = "";
		final List<Long> list = new ArrayList<>(this.resourceSet);
		if (!this.cmbThang.isEmpty()) {
			nam = this.cmbThang.getValue().toString().trim();
		} else {
			Notification.show("VUI LÒNG CHỌN NĂM XUẤT BÁO CÁO");
			this.cmbThang.focus();
			return;
		}
		if (!this.comboBox.isEmpty()) {
			thang1 = this.comboBox.getValue().toString().trim();
		} else {
			Notification.show("VUI LÒNG CHỌN THÁNG XUẤT BÁO CÁO");
			this.comboBox.focus();
			return;
		}

		String thang = thang1.substring(thang1.indexOf(" ")).trim();
		if (thang.length() == 1) {
			thang = "0" + thang;
		}

		String temporaryDirectory = "";
		if (SystemUtils.IS_OS_LINUX) {
			temporaryDirectory = System.getenv("REPORTING_PATH");
			temporaryDirectory = temporaryDirectory + "/monthly/"+nam+"/"+thang+"/";
			System.out.print(temporaryDirectory);
		} else {
			temporaryDirectory = "D:/";
		}

		if (this.checkBox.getValue() && Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId() == 1) {
			final String NameBCT = "BCTH_0_T"+ thang +"_"+nam;
			final String ReNameBCT = "BCTH_C08_"+ thang +"_"+nam;
			File source = new File("");
			
				source = new File(temporaryDirectory + NameBCT+".xlsx");
				// File (or directory) with new name
				final File fileRename = new File(temporaryDirectory + ReNameBCT+".xlsx");
//				source = new File("//10.0.3.237/up/reporting/monthly/" + nam + "/" + thang + "/"+ NameBCT + ".xlsx");
//				final File fileRename = new File("//10.0.3.237/up/reporting/monthly/" + nam + "/" + thang + "/" + ReNameBCT +".xlsx");
				final String path = source.getPath();
				LayFileExcel(path, ReNameBCT);
			
		} else {
			File source = new File("");
			for (final Long long1 : list) {
				final String NameBCM = "BCTH_"+long1+"_T"+thang+"_"+nam;
				final DonViCanhsatGt dv = new DonViCanhsatGtDAO().find(long1);
				final String maTrucThuoc = dv.getMaTrucThuoc();
				final String tenBC = dv.getTenVietTatDonViBc();
				final String ddhc  = dv.getDiaDanhHanhChinh().getTenVietTat();
				String ReNameBC = "";
				if(maTrucThuoc.equals("G01")){
					
				ReNameBC = "BCTH_C08_"+tenBC+"_"+thang+"_"+nam;
				}else{
						
				ReNameBC = "BCTH_PC08_"+ddhc+"_"+tenBC+"_"+thang+"_"+nam;
				}
				source = new File(temporaryDirectory + NameBCM+".xlsx");
						// File (or directory) with new name
				final File fileRename = new File(temporaryDirectory + ReNameBC+".xlsx");
				final String path = source.getPath();
				LayFileExcel(path, ReNameBC);
//				source = new File("//10.0.3.237/up/reporting/monthly/" + nam + "/" + thang + "/" +NameBCM+ ".xlsx");
//				final File fileRename = new File("//10.0.3.237/up/reporting/monthly/" + nam + "/" + thang + "/" +ReNameBC+".xlsx");
				
			}
		}}

	/**
	 * Event handler delegate method for the {@link XdevButton}
	 * {@link #btnQuarterReport}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnQuarterReport_buttonClick(final Button.ClickEvent event) {

		String nam = "";
		String quy1 = "";
		final List<Long> list = new ArrayList<>(this.resourceSet);
		if (!this.cmbQuy.isEmpty()) {
			nam = this.cmbQuy.getValue().toString().trim();
		} else {
			Notification.show("VUI LÒNG CHỌN NĂM XUẤT BÁO CÁO");
			this.cmbQuy.focus();
			return;
		}
		if (!this.cmbTenQuy.isEmpty()) {
			quy1 = this.cmbTenQuy.getValue().toString().trim();
		} else {
			Notification.show("VUI LÒNG CHỌN QUÝ XUẤT BÁO CÁO");
			this.cmbTenQuy.focus();
			return;
		}
		final String quy2= quy1.substring(quy1.indexOf(" ")).trim();
		String quy = quy1.substring(quy1.indexOf(" ")).trim();
		if (quy.length() == 1) {
			quy = "0" + quy;
		}
		String temporaryDirectory = "";

		if (this.checkBox.getValue() && Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId() == 1) {
			if (quy2.equalsIgnoreCase("1") || quy2.equalsIgnoreCase("2") || quy2.equalsIgnoreCase("3")
					|| quy2.equalsIgnoreCase("4")) {
				
				final String NameBCQuy = "BCTH_C08_0_Q"+quy2+"_"+nam;
				final String ReNameBCQuy = "BCTH_C08_"+quy2+"_"+nam;
				
								
				if (SystemUtils.IS_OS_LINUX) {
					temporaryDirectory = System.getenv("REPORTING_PATH");
					temporaryDirectory = temporaryDirectory + "/quarter/"+nam+"/"+quy+"/";
					System.out.print(temporaryDirectory);
				} else {
					temporaryDirectory = "D:/";
				}
				
				final File source = new File(temporaryDirectory + NameBCQuy+".xlsx");
				// File (or directory) with new name
//				final File fileRename = new File(temporaryDirectory + ReNameBCQuy+".xlsx");
				final String path = source.getPath();
				LayFileExcel(path, ReNameBCQuy);
				
								
//				final File source = new File("//10.0.3.237/up/reporting/quarter/" + nam +"/"+ quy + "/" + NameBCQuy + ".xlsx");
//				final File fileRename = new File("//10.0.3.237/up/reporting/quarter/" + nam +"/"+ quy + "/" + ReNameBCQuy + ".xlsx");
				
			} else {
				 String NameBCHalf ="";
				 String ReNameBCHalf = "";
				if(quy.equalsIgnoreCase("1+2")){
				  NameBCHalf = "01/BCTH_C08_0_Q1_Q2_" + nam;
				  ReNameBCHalf = "BCTH_C08_Q1_Q2_" + nam;
				}else if(quy.equalsIgnoreCase("3+4")){
				 NameBCHalf = "02/BCTH_C08_0_Q3_Q4_" + nam;
				 ReNameBCHalf = "BCTH_C08_Q3_Q4_" + nam;
				}
											
				if (SystemUtils.IS_OS_LINUX) {
					temporaryDirectory = System.getenv("REPORTING_PATH");
					temporaryDirectory = temporaryDirectory + "/half_year/"+nam+"/";
					System.out.print(temporaryDirectory);
				} else {
					temporaryDirectory = "D:/";
				}
				 final File fileRename = new File("");
				 File source = new File("");
				source = new File(temporaryDirectory + NameBCHalf +".xlsx");
				// File (or directory) with new name
//				 fileRename = new File(temporaryDirectory + ReNameBCHalf +".xlsx");
				 if (quy.equalsIgnoreCase("1+2")) {
					 source = new File(temporaryDirectory + NameBCHalf +".xlsx");
//					 fileRename = new File(temporaryDirectory + ReNameBCHalf +".xlsx");
					} else if (quy.equalsIgnoreCase("3+4")) {
						 source = new File(temporaryDirectory + NameBCHalf +".xlsx");
//						 fileRename = new File(temporaryDirectory + ReNameBCHalf +".xlsx");
					}
				 final String path = source.getPath();
					LayFileExcel(path, ReNameBCHalf);
				
//					if (quy.equalsIgnoreCase("1+2")) {
//						source = new File(
//								"//10.0.3.237/up/reporting/half_year/" + nam +"/"+ NameBCHalf +".xlsx");
//						fileRename = new File(
//								"//10.0.3.237/up/reporting/half_year/" + nam + "/"+ReNameBCHalf +".xlsx");
//					} else if (quy.equalsIgnoreCase("3+4")) {
//						source = new File(
//								"//10.0.3.237/up/reporting/half_year/" + nam + NameBCHalf +".xlsx");
//						fileRename = new File(
//								"//10.0.3.237/up/reporting/half_year/" + nam + ReNameBCHalf + ".xlsx");
//					}
			
			}
		} else {
			if (quy2.equalsIgnoreCase("1") || quy2.equalsIgnoreCase("2") || quy2.equalsIgnoreCase("3")
					|| quy2.equalsIgnoreCase("4")) {
				if (SystemUtils.IS_OS_LINUX) {
					temporaryDirectory = System.getenv("REPORTING_PATH");
					temporaryDirectory = temporaryDirectory + "/quarter/"+nam+"/"+quy+"/";
					System.out.print(temporaryDirectory);
				} else {
					temporaryDirectory = "D:/";
				}

				File source = new File("");
				for (final Long long1 : list) {
					final String NameBCQuy = "BCTH_"+long1+"_Q"+ quy2 +"_"+ nam;
					final DonViCanhsatGt dv = new DonViCanhsatGtDAO().find(long1);
					final String maTrucThuoc = dv.getMaTrucThuoc();
					final String tenBC = dv.getTenVietTatDonViBc();
					final String ddhc  = dv.getDiaDanhHanhChinh().getTenVietTat();
					String ReNameBC = "";
					if(maTrucThuoc.equals("G01")){
						
						ReNameBC = "BCTH_C08_"+ tenBC + quy1+"_"+ nam;
						}else{
							
						ReNameBC = "BCTH_PC08_"+ddhc+"_"+tenBC+"_"+ quy1 +"_"+nam;
						}
					source = new File(temporaryDirectory + NameBCQuy+".xlsx");
					final String path = source.getPath();
					LayFileExcel(path, ReNameBC);
					
//					source = new File("//10.0.3.237/up/reporting/quarter/" + nam +"/"+ quy + "/" + NameBCQuy + ".xlsx");
//					final File fileRename = new File("//10.0.3.237/up/reporting/quarter/" + nam +"/"+ quy + "/" + ReNameBC+".xlsx");
					
				}
			} else {
				if (SystemUtils.IS_OS_LINUX) {
					temporaryDirectory = System.getenv("REPORTING_PATH");
					temporaryDirectory = temporaryDirectory + "/half_year/"+nam+"/";
					System.out.print(temporaryDirectory);
				} else {
					temporaryDirectory = "D:/";
				}
				File source = new File("");
				final File fileRename = new File("");
				
				for (final Long long1 : list) {
					String NameBCHalf ="";
					 String ReNameBCHalf = "";
					final DonViCanhsatGt dv = new DonViCanhsatGtDAO().find(long1);
					final String maTrucThuoc = dv.getMaTrucThuoc();
					final String tenBC = dv.getTenVietTatDonViBc();
					final String ddhc  = dv.getDiaDanhHanhChinh().getTenVietTat();
					
					if(quy.equalsIgnoreCase("1+2") && maTrucThuoc.equals("G01")){
					  NameBCHalf = "01/BCTH_"+long1+"_Q1_Q2_" + nam;
					  ReNameBCHalf = "BCTH_C08_"+ tenBC +"_Q1_Q2_"+nam;
					 
					}else{
						NameBCHalf = "01/BCTH_"+long1+"_Q1_Q2_" + nam;
						ReNameBCHalf = "BCTH_PC08_"+ddhc+"_"+tenBC+"_Q1_Q2_"+nam;
					}
					if(quy.equalsIgnoreCase("3+4") && maTrucThuoc.equals("G01")){
						NameBCHalf = "02/BCTH_"+long1+"_Q3_Q4_" + nam;
						ReNameBCHalf = "BCTH_C08_"+ tenBC +"_Q3_Q4_"+nam;
					}else{
						NameBCHalf = "02/BCTH_"+long1+"_Q3_Q4_" + nam;
						ReNameBCHalf = "BCTH_PC08_"+ddhc+"_"+tenBC+"_Q3_Q4_"+nam;
					}
					
					 if (quy.equalsIgnoreCase("1+2")) {
						 source = new File(temporaryDirectory + NameBCHalf +".xlsx");
//					 fileRename = new File(temporaryDirectory + ReNameBCHalf +".xlsx");
					} else if (quy.equalsIgnoreCase("3+4")) {
						 source = new File(temporaryDirectory + NameBCHalf +".xlsx");
//						 fileRename = new File(temporaryDirectory + ReNameBCHalf +".xlsx");
					}
						final String path = source.getPath();
						LayFileExcel(path, ReNameBCHalf);
					 
					 
					 
//					if (quy.equalsIgnoreCase("1+2")) {
//						source = new File("//10.0.3.237/up/reporting/half_year/" + nam + "/" + NameBCHalf + ".xlsx");
//						fileRename = new File("//10.0.3.237/up/reporting/half_year/" + nam + "/" + ReNameBCHalf + ".xlsx");
//
//					} else if (quy.equalsIgnoreCase("3+4")) {
//						source = new File("//10.0.3.237/up/reporting/half_year/" + nam + "/" + NameBCHalf + ".xlsx");
//						fileRename = new File("//10.0.3.237/up/reporting/half_year/" + nam + "/" + ReNameBCHalf + ".xlsx");
//					}
					
				}
			}
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevButton}
	 * {@link #btnYearlyReport}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnYearlyReport_buttonClick(final Button.ClickEvent event) {

		String nam = "";
		final List<Long> list = new ArrayList<>(this.resourceSet);
//		if (list.size() < 1) {
//			Notification.show("VUI LÒNG CHỌN ĐƠN VỊ XUẤT BÁO CÁO");
//			return;
//		}
		if (!this.cmbQuy2.isEmpty()) {
			nam = this.cmbQuy2.getValue().toString().trim();
		} else {
			Notification.show("VUI LÒNG CHỌN NĂM XUẤT BÁO CÁO");
			this.cmbQuy2.focus();
			return;
		}
		String temporaryDirectory = "";
		if (SystemUtils.IS_OS_LINUX) {
			temporaryDirectory = System.getenv("REPORTING_PATH");
			temporaryDirectory = temporaryDirectory + "/yearly/"+nam+"/";
			System.out.print(temporaryDirectory);
		} else {
			temporaryDirectory = "D:/";
		}

		
		File source = new File("");
		if (this.checkBox.getValue() && Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId() == 1) {
			final String NameBC = "BCTH_0_"+nam;
			final String ReNameBC = "BCTH_C08_"+nam;
			
			source = new File(temporaryDirectory + NameBC+".xlsx");
			// File (or directory) with new name
//			final File fileRename = new File(temporaryDirectory + ReNameBC+".xlsx");
//			source = new File("//10.0.3.237/up/reporting/yearly/" + nam + "/" + NameBC + ".xlsx");
			final String path = source.getPath();
			System.out.println(path);
			LayFileExcel(path, ReNameBC);
			
		}

		else {
			for (final Long long1 : list) {
				final String NameBC = "BCTH_"+long1+"_"+nam;
				final DonViCanhsatGt dv = new DonViCanhsatGtDAO().find(long1);
				final String maTrucThuoc = dv.getMaTrucThuoc();
				final String tenBC = dv.getTenVietTatDonViBc();
				final String ddhc  = dv.getDiaDanhHanhChinh().getTenVietTat();
				String ReNameBC = "";
				
				if(maTrucThuoc.equals("G01")){
				
				ReNameBC = "BCTH_C08_"+tenBC+nam;
				}else{
					
				ReNameBC = "BCTH_PC08_"+ddhc+"_"+tenBC+"_"+nam;
				}
//				source = new File("//10.0.3.237/up/reporting/yearly/" + nam + "/" + NameBC + ".xlsx");
				source = new File(temporaryDirectory + NameBC+".xlsx");
				System.out.println(source.getPath());
				final String path = source.getPath();
				LayFileExcel(path, ReNameBC);
			}
		}
	}

	private void LayFileExcel(final String path, final String changedName){
		
		String temporaryDirectory = "";
		if (SystemUtils.IS_OS_LINUX) {
			temporaryDirectory = System.getenv("REPORTING_PATH");
			temporaryDirectory = temporaryDirectory + "/EmptyFile/";
			System.out.print(temporaryDirectory);
		} else {
			temporaryDirectory = "D:/";
		}
		
		File source1 = new File("");
		source1 = new File(temporaryDirectory +"EmptyFile.xlsx");
//		source1 = new File("//10.0.3.237/up/reporting/EmptyFile/EmptyFile.xlsx");
		final String path2 = source1.getPath();
		
		
		
		try {
            final StreamResource.StreamSource source = new StreamResource.StreamSource() {
            	boolean check = true;
                @Override
                public InputStream getStream() {
                    try {
                        return new FileInputStream(path);
                    } catch (final IOException e) {
                        this.check = false;
                        try {
							return new FileInputStream(path2);
						} catch (final FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                    }
                    return null;
                }
            };
            final StreamResource resource = new StreamResource(source, changedName  + ".xlsx");
            resource.getStream().setParameter("Content-Disposition", "attachment;filename=\""+changedName  + ".xlsx\"");
            resource.setMIMEType("application/xlsx");
            resource.setCacheTime(0);
//            this.browserFrame2.setSource(resource);
            Page.getCurrent().open(resource, "_blank", false);
//            }
//            else{
////            	final Report report = Report.New();
////    			final String reportSrc = "WebContent/WEB-INF/resources/Report.jrxml";
////    			report.jrxml(reportSrc);
////    			final List list = new ArrayList<>();
////    			report.dataSource(list);
////    			final Resource resource1 = report.exportToResource(ExportType.XLSX, changedName);
////    			Page.getCurrent().open(resource1, "_blank", false);
//
//            }
            
        } catch (final Exception e) {
            e.printStackTrace();
            Notification.show("Error!", Type.ERROR_MESSAGE);
        }
		
	}
	
	
	

//	private void button_buttonClick(final Button.ClickEvent event) {
//		ExcelExport excelExport;
//		excelExport = new ExcelExport(this.table);
//		excelExport.excludeCollapsedColumns();
//		excelExport.setReportTitle("Đơn vị CSGT ");
//		excelExport.setDisplayTotals(false);
//		excelExport.setExportFileName("donViCSGT.xls");
//		excelExport.export();
//	}

	/**
	 * Event handler delegate method for the {@link XdevCheckBox}
	 * {@link #checkBox}.
	 *
	 * @see Property.ValueChangeListener#valueChange(Property.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void checkBox_valueChange(final Property.ValueChangeEvent event) {
		if (this.checkBox.getValue() == true) {
			this.check = true;
		} else {
			this.check = false;
		}
	}

	/**
	 * Event handler delegate method for the {@link XdevButton}
	 * {@link #btnWeeklyReport3}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnWeeklyReport3_buttonClick(final Button.ClickEvent event) {
		// Báo cáo ngày=))
		final long CapDV = Authentication.getAuthUser().getDonViCanhsatGt().getCapDonVi().getId();
//		if (this.resourceSet.size() == 0) {
//			Notification.show("VUI LÒNG CHỌN ĐƠN VỊ XUẤT BÁO CÁO");
//			return;
//		}
		if (this.pdfNgay.isEmpty()) {
			Notification.show("VUI LÒNG CHỌN NGÀY XUẤT BÁO CÁO");
			this.pdfNgay.focus();
			return;
		}
		
		final String donvi = this.resourceSet.toString().substring(1, this.resourceSet.toString().length() - 1);
		if ((!this.pdfNgay.isEmpty() && CapDV != 1) || ((!this.pdfNgay.isEmpty() && CapDV == 1) && this.checkBox.getValue() == false )) {
			for(final long idDV : this.resourceSet){
			final HttpServletRequest request = null;
			final HttpServletResponse response = null;
			final Date fromdate = this.FromDate.getValue();
			final Date todate = this.ToDate.getValue();
			System.out.println("Create file excel");
			final XSSFWorkbook workbook = new XSSFWorkbook();
			final XSSFSheet sheet = workbook.createSheet("Báo Cáo Tổng Hợp");

			final XSSFFont font = workbook.createFont();
			final XSSFFont font1 = workbook.createFont();
			final XSSFFont fontRed = workbook.createFont();
			fontRed.setColor(HSSFColor.RED.index);
			fontRed.setBold(true);
			sheet.setColumnWidth(1, 3500);
			sheet.setColumnWidth(2, 8000);
			sheet.setColumnWidth(3, 8000);
			sheet.setColumnWidth(4, 5000);
			sheet.setColumnWidth(5, 10000);
			sheet.setColumnWidth(6, 5000);
			sheet.setColumnWidth(7, 15000);
			sheet.setColumnWidth(8, 5000);
			sheet.setColumnWidth(9, 5000);
			sheet.setColumnWidth(10, 5000);
			sheet.setColumnWidth(11, 5000);
			sheet.setColumnWidth(12, 5000);
			sheet.setColumnWidth(13, 5000);
			sheet.setColumnWidth(14, 5000);
			sheet.setColumnWidth(15, 25000);
			sheet.setColumnWidth(16, 7000);
			sheet.setColumnWidth(17, 5000);
			sheet.setColumnWidth(18, 25000);
			sheet.setColumnWidth(19, 5000);
			sheet.setColumnWidth(20, 5000);
			sheet.setColumnWidth(21, 5000);
			sheet.setColumnWidth(22, 6000);
			sheet.setColumnWidth(23, 6000);
			sheet.setColumnWidth(24, 5000);
			sheet.setColumnWidth(25, 5000);
			sheet.setColumnWidth(26, 5000);
			sheet.setColumnWidth(27, 5000);
			sheet.setColumnWidth(28, 6000);
			sheet.setColumnWidth(29, 6000);
			sheet.setColumnWidth(30, 5000);
			sheet.setColumnWidth(31, 5000);
			sheet.setColumnWidth(32, 5000);
			sheet.setColumnWidth(33, 5000);
			sheet.setColumnWidth(34, 5000);
			sheet.setColumnWidth(35, 5000);

			font.setBold(true);
			font.setFontName("Times New Roman");
			final XSSFCellStyle cellborder = workbook.createCellStyle();
			cellborder.setBorderBottom(BorderStyle.THIN);
			cellborder.setBorderTop(BorderStyle.THIN);
			cellborder.setBorderLeft(BorderStyle.THIN);
			cellborder.setBorderRight(BorderStyle.THIN);
			font1.setFontName("Times New Roman");
			cellborder.setFont(font1);
			cellborder.setAlignment(HorizontalAlignment.LEFT);
			cellborder.setWrapText(true);
			final XSSFCellStyle cellborderB = workbook.createCellStyle();
			// cellborderB.setBorderBottom(BorderStyle.THIN);
			// cellborderB.setBorderTop(BorderStyle.THIN);
			// cellborderB.setBorderLeft(BorderStyle.THIN);
			// cellborderB.setBorderRight(BorderStyle.THIN);
			cellborderB.setFont(font);
			cellborderB.setAlignment(HorizontalAlignment.CENTER);

			final XSSFCellStyle style = workbook.createCellStyle();
			final XSSFCellStyle style1 = workbook.createCellStyle();
			final XSSFCellStyle styleredbold = workbook.createCellStyle();
			final XSSFCellStyle stylecenter = workbook.createCellStyle();
			final XSSFCellStyle styleWrap = workbook.createCellStyle();
			final XSSFCellStyle styleUpper = workbook.createCellStyle();
			styleUpper.setFont(font1);
			final XSSFCellStyle styleWrapNoborder = workbook.createCellStyle();
			styleWrapNoborder.setAlignment(HorizontalAlignment.JUSTIFY);
			styleWrapNoborder.setWrapText(true);
			styleWrapNoborder.setFont(font);
			styleWrap.setWrapText(true);
			styleWrap.setBorderBottom(BorderStyle.THIN);
			styleWrap.setBorderTop(BorderStyle.THIN);
			styleWrap.setBorderLeft(BorderStyle.THIN);
			styleWrap.setBorderRight(BorderStyle.THIN);
			styleWrap.setFont(font1);
			stylecenter.setAlignment(HorizontalAlignment.CENTER);
			style1.setAlignment(HorizontalAlignment.CENTER);
			final XSSFFont fontBaocao = workbook.createFont();
			final Font fontBaocao1 = new Font();
			fontBaocao1.setSize(16);
			fontBaocao1.isBold();
			style1.setFont(font);
			style.setFont(font);
			styleredbold.setFont(font);
			int rowNum = 0;

			// tieu de
			final Row row = sheet.createRow(rowNum++);
			final Cell cell1 = row.createCell(0);
			final int cellrange = sheet.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));
			cell1.setCellValue("BÁO CÁO TỔNG HỢP");
			cell1.setCellStyle(style1);

			// tu ngay den ngay
			final Row rowNgay = sheet.createRow(rowNum++);
			final Cell cell2 = rowNgay.createCell(0);

			if (!this.pdfNgay.isEmpty()) {
				final Date fromDate = this.pdfNgay.getValue();
				final DateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");
				final int cellrange1 = sheet.addMergedRegionUnsafe(CellRangeAddress.valueOf("A2:F2"));
				cell2.setCellValue("Ngày " + dfm.format(fromDate));
				cell2.setCellStyle(style1);
				final DateFormat dfm1 = new SimpleDateFormat("dd-MMM-yy");
				this.fromdateBCTH = dfm1.format(fromDate);
			}
			// ten cot
			final Row row1 = sheet.createRow(rowNum++);
			final Cell A4 = row1.createCell(0);
			A4.setCellValue("STT");
			A4.setCellStyle(cellborderB);
			final Cell B4 = row1.createCell(1);
			B4.setCellValue("Mã vụ việc");
			B4.setCellStyle(cellborderB);
			final Cell C4 = row1.createCell(2);
			C4.setCellValue("Loại BB/QĐ");
			C4.setCellStyle(cellborderB);
			final Cell D4 = row1.createCell(3);
			D4.setCellValue("Số BB/QĐ");
			D4.setCellStyle(cellborderB);
			final Cell E4 = row1.createCell(4);
			E4.setCellValue("Đối tượng vi phạm");
			E4.setCellStyle(cellborderB);
			final Cell F4 = row1.createCell(5);
			F4.setCellValue("Tên");
			F4.setCellStyle(cellborderB);
			final Cell G4 = row1.createCell(6);
			G4.setCellValue("Thời gian vi phạm");
			G4.setCellStyle(cellborderB);
			final Cell H4 = row1.createCell(7);
			H4.setCellValue("Địa chỉ");
			H4.setCellStyle(cellborderB);
			final Cell I4 = row1.createCell(8);
			I4.setCellValue("Giới tính");
			I4.setCellStyle(cellborderB);
			final Cell J4 = row1.createCell(9);
			J4.setCellValue("Ngày sinh");
			J4.setCellStyle(cellborderB);
			final Cell K4 = row1.createCell(10);
			K4.setCellValue("Tuổi");
			K4.setCellStyle(cellborderB);
			final Cell L4 = row1.createCell(11);
			L4.setCellValue("Nghề nghiệp");
			L4.setCellStyle(cellborderB);
			final Cell M4 = row1.createCell(12);
			M4.setCellValue("Loại phương tiện");
			M4.setCellStyle(cellborderB);
			final Cell N4 = row1.createCell(13);
			N4.setCellValue("Biển số");
			N4.setCellStyle(cellborderB);
			final Cell O4 = row1.createCell(14);
			O4.setCellValue("Địa điểm vi phạm");
			O4.setCellStyle(cellborderB);
			final Cell P4 = row1.createCell(15);
			P4.setCellValue("Nội dung vi phạm");
			P4.setCellStyle(cellborderB);
			final Cell Q4 = row1.createCell(16);
			Q4.setCellValue("Điều, khoản, điểm, VP/ND");
			Q4.setCellStyle(cellborderB);
			final Cell R4 = row1.createCell(17);
			R4.setCellValue("Nhóm HVVP");
			R4.setCellStyle(cellborderB);
			final Cell S4 = row1.createCell(18);
			S4.setCellValue("Tạm giữ");
			S4.setCellStyle(cellborderB);
			final Cell T4 = row1.createCell(19);
			T4.setCellValue("Hạng giấy tờ");
			T4.setCellStyle(cellborderB);
			final Cell U4 = row1.createCell(20);
			U4.setCellValue("Số giấy tờ tạm giữ");
			U4.setCellStyle(cellborderB);
			final Cell V4 = row1.createCell(21);
			V4.setCellValue("Thời hạn giấy tờ");
			V4.setCellStyle(cellborderB);
			final Cell W4 = row1.createCell(22);
			W4.setCellValue("Nơi cấp giấy tờ");
			W4.setCellStyle(cellborderB);
			final Cell X4 = row1.createCell(23);
			X4.setCellValue("Thời hạn tạm giữ phương tiện");
			X4.setCellStyle(cellborderB);
			final Cell Y4 = row1.createCell(24);
			Y4.setCellValue("Hình thức xử phạt");
			Y4.setCellStyle(cellborderB);

			final Cell Z4 = row1.createCell(25);
			Z4.setCellValue("Số tiền");
			Z4.setCellStyle(cellborderB);
			final Cell AA4 = row1.createCell(26);
			AA4.setCellValue("Hình thức nộp phạt");
			AA4.setCellStyle(cellborderB);
			final Cell AB4 = row1.createCell(27);
			AB4.setCellValue("Hình phạt bổ sung");
			AB4.setCellStyle(cellborderB);
			final Cell AC4 = row1.createCell(28);
			AC4.setCellValue("Thời gian bắt đầu HPBS");
			AC4.setCellStyle(cellborderB);
			final Cell AD4 = row1.createCell(29);
			AD4.setCellValue("Thời gian kết thức HPBS");
			AD4.setCellStyle(cellborderB);
			final Cell AE4 = row1.createCell(30);
			AE4.setCellValue("Biện pháp khắc phục hậu quả");
			AE4.setCellStyle(cellborderB);
			final Cell AT4 = row1.createCell(31);
			AT4.setCellValue("Tang vật trả lại");
			AT4.setCellStyle(cellborderB);
			final Cell AF4 = row1.createCell(32);
			AF4.setCellValue("Ngày lập");
			AF4.setCellStyle(cellborderB);
			final Cell AM4 = row1.createCell(33);
			AM4.setCellValue("Đơn vị lập");
			AM4.setCellStyle(cellborderB);
			final Cell AG4 = row1.createCell(34);
			AG4.setCellValue("Đơn vị xử lý");
			AG4.setCellStyle(cellborderB);
			final Cell AH4 = row1.createCell(35);
			AH4.setCellValue("Cán bộ xử lý");
			AH4.setCellStyle(cellborderB);
			final Cell AI4 = row1.createCell(36);
			AI4.setCellValue("Cấp phê duyệt");
			AI4.setCellStyle(cellborderB);
			final Cell AJ4 = row1.createCell(37);
			AJ4.setCellValue("Lãnh đạo phê duyệt");
			AJ4.setCellStyle(cellborderB);
			final Cell AK4 = row1.createCell(38);
			AK4.setCellValue("Địa bàn vi phạm");
			AK4.setCellStyle(cellborderB);
			final Cell AL4 = row1.createCell(39);
			AL4.setCellValue("Lĩnh vực");
			AL4.setCellStyle(cellborderB);

			final Table table = new Table();
			int count1 = 1;
			table.addContainerProperty("STT", String.class, null);
			table.addContainerProperty("MA_VU_VIEC", String.class, null);
			table.addContainerProperty("MA_RUTGON", String.class, null);
			table.addContainerProperty("LOAI_BB_QD", String.class, null);
			table.addContainerProperty("SO_BIEN_BAN", String.class, null);
			table.addContainerProperty("TEN_NGUOI_NVP", String.class, null);
			table.addContainerProperty("DIA_DANH_HC_ID", String.class, null);
			table.addContainerProperty("DIA_CHI_NVP", String.class, null);
			table.addContainerProperty("NGAY_SINH_NVP_NHAP", String.class, null);
			table.addContainerProperty("NGHE_NGHIEP_NVP", String.class, null);
			table.addContainerProperty("LOAI_PHUONG_TIEN", String.class, null);
			table.addContainerProperty("NOI_DUNG_VPHC", String.class, null);
			table.addContainerProperty("BIEN_KIEM_SOAT", String.class, null);
			table.addContainerProperty("HANG_GPLX", String.class, null);
			table.addContainerProperty("GPLX", String.class, null);
			table.addContainerProperty("THOI_GIAN_VPHC", String.class, null);
			table.addContainerProperty("DIA_DIEM_VPHC", String.class, null);
			table.addContainerProperty("TANG_VAT_TG", String.class, null);
			table.addContainerProperty("THOI_HAN_TG", String.class, null);
			table.addContainerProperty("HINH_THUC_XP", String.class, null);
			table.addContainerProperty("TONG_MUC_PHAT", String.class, null);
			table.addContainerProperty("XU_PHAT_BO_SUNG", String.class, null);
			table.addContainerProperty("TU_NGAY_XPBS", String.class, null);
			table.addContainerProperty("DEN_NGAY_XPBS", String.class, null);
			table.addContainerProperty("BIEN_PHAP_KHAC_PHUC", String.class, null);
			table.addContainerProperty("NGAY_LAP_BB", Date.class, null);
			table.addContainerProperty("TEN_DON_VI", String.class, null);
			table.addContainerProperty("TEN_CAN_BO", String.class, null);
			table.addContainerProperty("LINH_VUC_GIAO_THONG", String.class, null);
			table.addContainerProperty("TO_CHUC", String.class, null);
			table.addContainerProperty("KIEM_DINH", String.class, null);
			table.addContainerProperty("TRANG_THAI_NP", String.class, null);
			table.addContainerProperty("HANH_VI_VP_ID", String.class, null);
			table.addContainerProperty("LUAT_TC_ID", String.class, null);
			table.addContainerProperty("TUOC_TU_NGAY", String.class, null);
			table.addContainerProperty("TUOC_DEN_NGAY", String.class, null);
			table.addContainerProperty("CAP_BAC_CHUC_VU", String.class, null);
			table.addContainerProperty("DON_VI_THU_TIEN", String.class, null);
			table.addContainerProperty("TANG_VAT_TRA_LAI", String.class, null);
			table.addContainerProperty("TEN_DON_VI_LAP", String.class, null);
			table.addContainerProperty("LOAI_TVTG_ID", String.class, null);
			table.addContainerProperty("LOAI_TVTG", String.class, null);
			table.addContainerProperty("HANG_TVTG", String.class, null);
			table.addContainerProperty("NOI_CAP_TVTG", String.class, null);
			table.addContainerProperty("HIEU_LUC_TVTG", String.class, null);
			table.addContainerProperty("TINH_TRANG", String.class, null);
			table.addContainerProperty("SO_LUONG", String.class, null);
			table.addContainerProperty("DON_VI_TINH", String.class, null);
			table.addContainerProperty("SO_TVTG", String.class, null);
			table.addContainerProperty("NOI_DUNG_HVVP", String.class, null);
			table.addContainerProperty("DIEU_LUAT", String.class, null);
			table.addContainerProperty("THOI_HAN_TU", String.class, null);
			table.addContainerProperty("THOI_HAN_DEN", String.class, null);
			table.addContainerProperty("TANG_VAT", String.class, null);

			// final String donvi = Bao_Cao_Tong_Hop.this.resourceDv.toString();

			final ViewBaocaothQd01DAO dao01 = new ViewBaocaothQd01DAO();
			final List<ViewBaocaothQd01> listVuViec01;
			if (this.danhSachBCTH != "") {
				listVuViec01 = dao01.BaoCaoTongHopNgay(String.valueOf(idDV), this.fromdateBCTH);
			} else {
				listVuViec01 = dao01.BaoCaoTongHopNgay("", this.fromdateBCTH);
			}
			for (final ViewBaocaothQd01 vuviec01 : listVuViec01) {
				count1++;
				table.addItem(new Object[] { String.valueOf(listVuViec01.indexOf(vuviec01) + 1),
						String.valueOf(vuviec01.getMaVuViec()), String.valueOf(vuviec01.getMaRutgon()),
						String.valueOf(vuviec01.getLoaiBbQd()), String.valueOf(vuviec01.getSoBienBan()),
						String.valueOf(vuviec01.getTenNguoiNvp()), String.valueOf(vuviec01.getDiaDanhHcId()),
						vuviec01.getDiaChiNvp(), vuviec01.getNgaySinhNvpNhap(), vuviec01.getNgheNghiepNvp(),
						vuviec01.getLoaiPhuongTien(), vuviec01.getNoiDungVphc(), vuviec01.getBienKiemSoat(),
						vuviec01.getHangGplx(), vuviec01.getGplx(), vuviec01.getThoiGianVphc(),
						vuviec01.getDiaDiemVphc(), vuviec01.getTangVatTg(), vuviec01.getThoiHanTg(),
						vuviec01.getHinhThucXp(),
						vuviec01.getTongMucPhat() == null ? null : (String.valueOf(vuviec01.getTongMucPhat())),
						vuviec01.getXuPhatBoSung(),
						vuviec01.getTuNgayXpbs() == null ? "" : (String.valueOf(vuviec01.getTuNgayXpbs())),
						vuviec01.getDenNgayXpbs() == null ? "" : (String.valueOf(vuviec01.getDenNgayXpbs())),
						vuviec01.getBienPhapKhacPhuc(), vuviec01.getNgayLapBb(), vuviec01.getTenDonVi(),
						vuviec01.getTenCanBo(),
						vuviec01.getLinhVucGiaoThong() == null ? "" : String.valueOf(vuviec01.getLinhVucGiaoThong()),
						String.valueOf(vuviec01.getToChuc()), vuviec01.getKiemDinh(),
						vuviec01.getTrangThaiNp() == null ? "" : String.valueOf(vuviec01.getTrangThaiNp()),
						vuviec01.getHanhViVpId() == null ? "" : String.valueOf(vuviec01.getHanhViVpId()),
						vuviec01.getLuatTcId() == null ? "" : String.valueOf(vuviec01.getLuatTcId()),
						vuviec01.getTuocTuNgay() == null ? "" : (String.valueOf(vuviec01.getTuocTuNgay())),
						vuviec01.getTuocDenNgay() == null ? "" : (String.valueOf(vuviec01.getTuocDenNgay())),
						vuviec01.getCapBacChucVu(), vuviec01.getDonViThuTien(), vuviec01.getTangVatTraLai(),
						vuviec01.getTenDonViLap(), vuviec01.getLoaiTvtgId(), vuviec01.getLoaiTvtg(),
						vuviec01.getHangTvtg(), vuviec01.getNoiCapTvtg(), vuviec01.getHieuLucTvtg(),
						vuviec01.getTinhTrang(), vuviec01.getSoLuong(), vuviec01.getDonViTinh(), vuviec01.getSoTvtg(),
						vuviec01.getNoiDungHvvp(), vuviec01.getDieuLuat(), vuviec01.getThoiHanTu(),
						vuviec01.getThoiHanDen(), vuviec01.getTangVat() }, count1);

			}
			final ViewBaocaothBb43DAO dao43 = new ViewBaocaothBb43DAO();
			final List<ViewBaocaothBb43> listVuViec43;
			if (this.danhSachBCTH != "") {
				listVuViec43 = dao43.BaoCaoTongHopNgay(String.valueOf(idDV), this.fromdateBCTH);
			} else {
				listVuViec43 = dao43.BaoCaoTongHopNgay("", this.fromdateBCTH);
			}
			for (final ViewBaocaothBb43 vuviec43 : listVuViec43) {
				count1++;
				table.addItem(new Object[] { String.valueOf(listVuViec43.indexOf(vuviec43) + 1), vuviec43.getMaVuViec(),
						vuviec43.getMaRutgon(), String.valueOf(vuviec43.getLoaiBbQd()), vuviec43.getSoBienBan(),
						vuviec43.getTenNguoiNvp(), String.valueOf(vuviec43.getDiaDanhHcId()), vuviec43.getDiaChiNvp(),
						vuviec43.getNgaySinhNvpNhap(), vuviec43.getNgheNghiepNvp(), vuviec43.getLoaiPhuongTien(),
						vuviec43.getNoiDungVphc(), vuviec43.getBienKiemSoat(), vuviec43.getHangGplx(),
						vuviec43.getGplx(), vuviec43.getThoiGianVphc(), vuviec43.getDiaDiemVphc(),
						vuviec43.getTangVatTg(), vuviec43.getThoiHanTg(), vuviec43.getHinhThucXp(),
						vuviec43.getTongMucPhat() == null ? "" : String.valueOf(vuviec43.getTongMucPhat()),
						vuviec43.getXuPhatBoSung(), vuviec43.getTuNgayXpbs(), vuviec43.getDenNgayXpbs(),
						vuviec43.getBienPhapKhacPhuc(), vuviec43.getNgayLapBb(), vuviec43.getTenDonVi(),
						vuviec43.getTenCanBo(),
						vuviec43.getLinhVucGiaoThong() == null ? "" : String.valueOf(vuviec43.getLinhVucGiaoThong()),
						String.valueOf(vuviec43.getToChuc()), vuviec43.getKiemDinh(), vuviec43.getTrangThaiNp(),
						String.valueOf(vuviec43.getHanhViVpId()), String.valueOf(vuviec43.getLuatTcId()),
						vuviec43.getTuocTuNgay() == null ? "" : (String.valueOf(vuviec43.getTuocTuNgay())),
						vuviec43.getTuocDenNgay() == null ? "" : (String.valueOf(vuviec43.getTuocDenNgay())),
						vuviec43.getCapBacChucVu(), vuviec43.getDonViThuTien(), vuviec43.getTangVatTraLai(),
						vuviec43.getTenDonViLap(), String.valueOf(vuviec43.getLoaiTvtgId()), vuviec43.getLoaiTvtg(),
						vuviec43.getHangTvtg(), vuviec43.getNoiCapTvtg(), vuviec43.getHieuLucTvtg(),
						vuviec43.getTinhTrang(), String.valueOf(vuviec43.getSoLuong()), vuviec43.getDonViTinh(),
						vuviec43.getSoTvtg(), vuviec43.getNoiDungHvvp(), vuviec43.getDieuLuat(),
						vuviec43.getThoiHanTu(), vuviec43.getThoiHanDen(), vuviec43.getTangVat() }, count1);

			}
			final ViewBaocaothBb50DAO dao50 = new ViewBaocaothBb50DAO();
			final List<ViewBaocaothBb50> listVuViec50;
			listVuViec50 = dao50.BaoCaoTongHopNgay(String.valueOf(idDV), this.fromdateBCTH);
			for (final ViewBaocaothBb50 vuviec50 : listVuViec50) {
				count1++;
				table.addItem(new Object[] { String.valueOf(listVuViec50.indexOf(vuviec50) + 1), vuviec50.getMaVuViec(),
						vuviec50.getMaRutgon(), String.valueOf(vuviec50.getLoaiBbQd()), vuviec50.getSoBienBan(),
						vuviec50.getTenNguoiNvp(), String.valueOf(vuviec50.getDiaDanhHcId()), vuviec50.getDiaChiNvp(),
						vuviec50.getNgaySinhNvpNhap(), vuviec50.getNgheNghiepNvp(), vuviec50.getLoaiPhuongTien(),
						vuviec50.getNoiDungVphc(), vuviec50.getBienKiemSoat(), vuviec50.getHangGplx(),
						vuviec50.getGplx(), vuviec50.getThoiGianVphc(), vuviec50.getDiaDiemVphc(),
						vuviec50.getTangVatTg(), vuviec50.getThoiHanTg(), vuviec50.getHinhThucXp(),
						vuviec50.getTongMucPhat(), vuviec50.getXuPhatBoSung(), vuviec50.getTuNgayXpbs(),
						vuviec50.getDenNgayXpbs(), vuviec50.getBienPhapKhacPhuc(), vuviec50.getNgayLapBb(),
						vuviec50.getTenDonVi(), vuviec50.getTenCanBo(),
						vuviec50.getLinhVucGiaoThong() == null ? "" : String.valueOf(vuviec50.getLinhVucGiaoThong()),
						String.valueOf(vuviec50.getToChuc()), vuviec50.getKiemDinh(), vuviec50.getTrangThaiNp(),
						vuviec50.getHanhViVpId(), vuviec50.getLuatTcId(), vuviec50.getTuocTuNgay(),
						vuviec50.getTuocDenNgay(), vuviec50.getCapBacChucVu(), vuviec50.getDonViThuTien(),
						vuviec50.getTangVatTraLai(), vuviec50.getTenDonViLap(),
						String.valueOf(vuviec50.getLoaiTvtgId()), vuviec50.getLoaiTvtg(), vuviec50.getHangTvtg(),
						vuviec50.getNoiCapTvtg(), vuviec50.getHieuLucTvtg(), vuviec50.getTinhTrang(),
						String.valueOf(vuviec50.getSoLuong()), vuviec50.getDonViTinh(), vuviec50.getSoTvtg(),
						vuviec50.getNoiDungHvvp(), vuviec50.getDieuLuat(), vuviec50.getThoiHanTu(),
						vuviec50.getThoiHanDen(), vuviec50.getTangVat() }, count1);

			}
			final ViewBaocaothBb60DAO dao60 = new ViewBaocaothBb60DAO();
			final List<ViewBaocaothBb60> listVuViec60;
			listVuViec60 = dao60.BaoCaoTongHopNgay(String.valueOf(idDV), this.fromdateBCTH);
			for (final ViewBaocaothBb60 vuviec60 : listVuViec60) {
				count1++;
				table.addItem(new Object[] { String.valueOf(listVuViec60.indexOf(vuviec60) + 1), vuviec60.getMaVuViec(),
						vuviec60.getMaRutgon(), String.valueOf(vuviec60.getLoaiBbQd()), vuviec60.getSoBienBan(),
						vuviec60.getTenNguoiNvp(), String.valueOf(vuviec60.getDiaDanhHcId()), vuviec60.getDiaChiNvp(),
						vuviec60.getNgaySinhNvpNhap(), vuviec60.getNgheNghiepNvp(), vuviec60.getLoaiPhuongTien(),
						vuviec60.getNoiDungVphc(), vuviec60.getBienKiemSoat(), vuviec60.getHangGplx(),
						vuviec60.getGplx(), vuviec60.getThoiGianVphc(), vuviec60.getDiaDiemVphc(),
						vuviec60.getTangVatTg(), vuviec60.getThoiHanTg(), vuviec60.getHinhThucXp(),
						vuviec60.getTongMucPhat(), vuviec60.getXuPhatBoSung(), vuviec60.getTuNgayXpbs(),
						vuviec60.getDenNgayXpbs(), vuviec60.getBienPhapKhacPhuc(), vuviec60.getNgayLapBb(),
						vuviec60.getTenDonVi(), vuviec60.getTenCanBo(),
						vuviec60.getLinhVucGiaoThong() == null ? "" : String.valueOf(vuviec60.getLinhVucGiaoThong()),
						String.valueOf(vuviec60.getToChuc()), vuviec60.getKiemDinh(), vuviec60.getTrangThaiNp(),
						vuviec60.getHanhViVpId(), vuviec60.getLuatTcId(), vuviec60.getTuocTuNgay(),
						vuviec60.getTuocDenNgay(), vuviec60.getCapBacChucVu(), vuviec60.getDonViThuTien(),
						vuviec60.getTangVatTraLai(), vuviec60.getTenDonViLap(),
						String.valueOf(vuviec60.getLoaiTvtgId()), vuviec60.getLoaiTvtg(), vuviec60.getHangTvtg(),
						vuviec60.getNoiCapTvtg(), vuviec60.getHieuLucTvtg(), vuviec60.getTinhTrang(),
						String.valueOf(vuviec60.getSoLuong()), vuviec60.getDonViTinh(), vuviec60.getSoTvtg(),
						vuviec60.getNoiDungHvvp(), vuviec60.getDieuLuat(), vuviec60.getThoiHanTu(),
						vuviec60.getThoiHanDen(), vuviec60.getTangVat() }, count1);

			}

			final ViewBaocaothQd02DAO dao02 = new ViewBaocaothQd02DAO();
			final List<ViewBaocaothQd02> listVuViec02;
			listVuViec02 = dao02.BaoCaoTongHopNgay(String.valueOf(idDV), this.fromdateBCTH);
			for (final ViewBaocaothQd02 vuviec02 : listVuViec02) {
				final String stt = String.valueOf(listVuViec02.indexOf(vuviec02) + 1);
				count1++;
				table.addItem(new Object[] { stt, String.valueOf(vuviec02.getMaVuViec()),
						String.valueOf(vuviec02.getMaRutgon()), String.valueOf(vuviec02.getLoaiBbQd()),
						String.valueOf(vuviec02.getSoBienBan()), String.valueOf(vuviec02.getTenNguoiNvp()),
						String.valueOf(vuviec02.getDiaDanhHcId()), vuviec02.getDiaChiNvp(),
						vuviec02.getNgaySinhNvpNhap(), vuviec02.getNgheNghiepNvp(), vuviec02.getLoaiPhuongTien(),
						vuviec02.getNoiDungVphc(), vuviec02.getBienKiemSoat(), vuviec02.getHangGplx(),
						vuviec02.getGplx(), vuviec02.getThoiGianVphc(), vuviec02.getDiaDiemVphc(),
						vuviec02.getTangVatTg(), vuviec02.getThoiHanTg(), vuviec02.getHinhThucXp(),
						vuviec02.getTongMucPhat() == null ? null : (String.valueOf(vuviec02.getTongMucPhat())),
						vuviec02.getXuPhatBoSung(),
						vuviec02.getTuNgayXpbs() == null ? "" : (String.valueOf(vuviec02.getTuNgayXpbs())),
						vuviec02.getDenNgayXpbs() == null ? "" : (String.valueOf(vuviec02.getDenNgayXpbs())),
						vuviec02.getBienPhapKhacPhuc(), vuviec02.getNgayLapBb(), vuviec02.getTenDonVi(),
						vuviec02.getTenCanBo(),
						vuviec02.getLinhVucGiaoThong() == null ? "" : String.valueOf(vuviec02.getLinhVucGiaoThong()),
						String.valueOf(vuviec02.getToChuc()), vuviec02.getKiemDinh(),
						vuviec02.getTrangThaiNp() == null ? "" : String.valueOf(vuviec02.getTrangThaiNp()),
						vuviec02.getHanhViVpId() == null ? "" : String.valueOf(vuviec02.getHanhViVpId()),
						vuviec02.getLuatTcId() == null ? "" : String.valueOf(vuviec02.getLuatTcId()),
						vuviec02.getTuocTuNgay() == null ? "" : (String.valueOf(vuviec02.getTuocTuNgay())),
						vuviec02.getTuocDenNgay() == null ? "" : (String.valueOf(vuviec02.getTuocDenNgay())),
						vuviec02.getCapBacChucVu(), vuviec02.getDonViThuTien(), vuviec02.getTangVatTraLai(),
						vuviec02.getTenDonViLap(), vuviec02.getLoaiTvtgId(), vuviec02.getLoaiTvtg(),
						vuviec02.getHangTvtg(), vuviec02.getNoiCapTvtg(), vuviec02.getHieuLucTvtg(),
						vuviec02.getTinhTrang(), vuviec02.getSoLuong(), vuviec02.getDonViTinh(), vuviec02.getSoTvtg(),
						vuviec02.getNoiDungHvvp(), vuviec02.getDieuLuat(), vuviec02.getThoiHanTu(),
						vuviec02.getThoiHanDen(), vuviec02.getTangVat() }, count1);

			}
			final ViewBaocaothQd18DAO dao18 = new ViewBaocaothQd18DAO();
			final List<ViewBaocaothQd18> listVuViec18;
			listVuViec18 = dao18.BaoCaoTongHopNgay(String.valueOf(idDV), this.fromdateBCTH);
			for (final ViewBaocaothQd18 vuviec18 : listVuViec18) {
				count1++;
				table.addItem(new Object[] { String.valueOf(listVuViec18.indexOf(vuviec18) + 1),
						String.valueOf(vuviec18.getMaVuViec()), String.valueOf(vuviec18.getMaRutgon()),
						String.valueOf(vuviec18.getLoaiBbQd()), String.valueOf(vuviec18.getSoBienBan()),
						String.valueOf(vuviec18.getTenNguoiNvp()), String.valueOf(vuviec18.getDiaDanhHcId()),
						vuviec18.getDiaChiNvp(), vuviec18.getNgaySinhNvpNhap(), vuviec18.getNgheNghiepNvp(),
						vuviec18.getLoaiPhuongTien(), vuviec18.getNoiDungVphc(), vuviec18.getBienKiemSoat(),
						vuviec18.getHangGplx(), vuviec18.getGplx(), vuviec18.getThoiGianVphc(),
						vuviec18.getDiaDiemVphc(), vuviec18.getTangVatTg(), vuviec18.getThoiHanTg(),
						vuviec18.getHinhThucXp(), vuviec18.getTongMucPhat(), vuviec18.getXuPhatBoSung(),
						vuviec18.getTuNgayXpbs(), vuviec18.getDenNgayXpbs(), vuviec18.getBienPhapKhacPhuc(),
						vuviec18.getNgayLapBb(), vuviec18.getTenDonVi(), vuviec18.getTenCanBo(),
						vuviec18.getLinhVucGiaoThong() == null ? "" : String.valueOf(vuviec18.getLinhVucGiaoThong()),
						String.valueOf(vuviec18.getToChuc()), vuviec18.getKiemDinh(), vuviec18.getTrangThaiNp(),
						vuviec18.getHanhViVpId(), vuviec18.getLuatTcId(), vuviec18.getTuocTuNgay(),
						vuviec18.getTuocDenNgay(), vuviec18.getCapBacChucVu(), vuviec18.getDonViThuTien(),
						vuviec18.getTangVatTraLai(), vuviec18.getTenDonViLap(),
						String.valueOf(vuviec18.getLoaiTvtgId()), vuviec18.getLoaiTvtg(), vuviec18.getHangTvtg(),
						vuviec18.getNoiCapTvtg(), vuviec18.getHieuLucTvtg(), vuviec18.getTinhTrang(),
						String.valueOf(vuviec18.getSoLuong()), vuviec18.getDonViTinh(), vuviec18.getSoTvtg(),
						vuviec18.getNoiDungHvvp(), vuviec18.getDieuLuat(),
						vuviec18.getThoiHanTu() == null ? "" : (String.valueOf(vuviec18.getThoiHanTu())),
						vuviec18.getThoiHanDen() == null ? "" : (String.valueOf(vuviec18.getThoiHanDen())),
						vuviec18.getTangVat() }, count1);
				;

			}
			final ViewBaocaothQd20DAO dao20 = new ViewBaocaothQd20DAO();
			final List<ViewBaocaothQd20> listVuViec20;
			listVuViec20 = dao20.BaoCaoTongHopNgay(String.valueOf(idDV), this.fromdateBCTH);
			for (final ViewBaocaothQd20 vuviec20 : listVuViec20) {
				count1++;
				table.addItem(new Object[] { String.valueOf(listVuViec20.indexOf(vuviec20) + 1),
						String.valueOf(vuviec20.getMaVuViec()), String.valueOf(vuviec20.getMaRutgon()),
						String.valueOf(vuviec20.getLoaiBbQd()), String.valueOf(vuviec20.getSoBienBan()),
						String.valueOf(vuviec20.getTenNguoiNvp()), String.valueOf(vuviec20.getDiaDanhHcId()),
						vuviec20.getDiaChiNvp(), vuviec20.getNgaySinhNvpNhap(), vuviec20.getNgheNghiepNvp(),
						vuviec20.getLoaiPhuongTien(), vuviec20.getNoiDungVphc(), vuviec20.getBienKiemSoat(),
						vuviec20.getHangGplx(), vuviec20.getGplx(), vuviec20.getThoiGianVphc(),
						vuviec20.getDiaDiemVphc(), vuviec20.getTangVatTg(), vuviec20.getThoiHanTg(),
						vuviec20.getHinhThucXp(), vuviec20.getTongMucPhat(), vuviec20.getXuPhatBoSung(),
						vuviec20.getTuNgayXpbs(), vuviec20.getDenNgayXpbs(), vuviec20.getBienPhapKhacPhuc(),
						vuviec20.getNgayLapBb(), vuviec20.getTenDonVi(), vuviec20.getTenCanBo(),
						vuviec20.getLinhVucGiaoThong() == null ? "" : String.valueOf(vuviec20.getLinhVucGiaoThong()),
						String.valueOf(vuviec20.getToChuc()), vuviec20.getKiemDinh(), vuviec20.getTrangThaiNp(),
						vuviec20.getHanhViVpId(), vuviec20.getLuatTcId(), vuviec20.getTuocTuNgay(),
						vuviec20.getTuocDenNgay(), vuviec20.getCapBacChucVu(), vuviec20.getDonViThuTien(),
						vuviec20.getTangVatTraLai(), vuviec20.getTenDonViLap(),
						String.valueOf(vuviec20.getLoaiTvtgId()), vuviec20.getLoaiTvtg(), vuviec20.getHangTvtg(),
						vuviec20.getNoiCapTvtg(), vuviec20.getHieuLucTvtg(), vuviec20.getTinhTrang(),
						String.valueOf(vuviec20.getSoLuong()), vuviec20.getDonViTinh(), vuviec20.getSoTvtg(),
						vuviec20.getNoiDungHvvp(), vuviec20.getDieuLuat(), vuviec20.getThoiHanTu(),
						vuviec20.getThoiHanDen(), vuviec20.getTangVat() }, count1);

			}
			for (final Object i : table.getItemIds()) {

				final Property propertyMaVV = table.getContainerProperty(i, "MA_RUTGON");
				final Row row43 = sheet.createRow(rowNum++);
				final Cell A5 = row43.createCell(0);
				A5.setCellValue(row43.getRowNum() - 2);
				final Cell B5 = row43.createCell(1);
				B5.setCellValue((String) propertyMaVV.getValue());

				final Property propertyLOAI_BB_QD = table.getContainerProperty(i, "LOAI_BB_QD");
				final long loaiBB = Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim());

				final Cell C5 = row43.createCell(2);
				if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 43) {
					C5.setCellValue("Biên bản vi phạm hành chính");
				} else if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 2) {
					C5.setCellValue("Quyết định xử phạt vi phạm hành chính");
				} else if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 1) {
					C5.setCellValue("Quyết định xử phạt vi phạm hành chính không lập biên bản");
				} else if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 18) {
					C5.setCellValue("Quyết định tạm giữ tang vật phương tiện");
				} else if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 20) {
					C5.setCellValue("Quyết định trả lại tang vật phương tiện");
				} else if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 60) {
					C5.setCellValue("Biên bản trả lại tang vật phương tiện");
				} else if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 50) {
					C5.setCellValue("Biên bản tạm giữ tang vật phương tiện");
				}

				final Property propertySoBB = table.getContainerProperty(i, "SO_BIEN_BAN");
				final Cell D5 = row43.createCell(3);
				D5.setCellValue(propertySoBB.getValue().toString());

				final Property propertyTC = table.getContainerProperty(i, "TO_CHUC");
				final Cell E5 = row43.createCell(4);
				if (propertyTC.getValue() != null) {
					if (Long.parseLong(propertyTC.getValue().toString().trim()) == 0) {
						E5.setCellValue("Cá nhân");
					} else if (Long.parseLong(propertyTC.getValue().toString().trim()) == 1) {
						E5.setCellValue("Tổ chức");
					}
				} else {
					E5.setCellValue("");
				}

				final Property propertyTen = table.getContainerProperty(i, "TEN_NGUOI_NVP");
				final Cell F5 = row43.createCell(5);
				F5.setCellValue((String) propertyTen.getValue());

				final Property propertyThoiGianVphc = table.getContainerProperty(i, "THOI_GIAN_VPHC");
				final Cell G5 = row43.createCell(6);
				G5.setCellValue((String) propertyThoiGianVphc.getValue());

				final Property propertyDiaChi = table.getContainerProperty(i, "DIA_CHI_NVP");
				final Cell H5 = row43.createCell(7);
				H5.setCellValue((String) propertyDiaChi.getValue());

				final Cell I5 = row43.createCell(8);
				I5.setCellValue("");

				final Property propertyNgaySinh = table.getContainerProperty(i, "NGAY_SINH_NVP_NHAP");
				final Cell J5 = row43.createCell(9);
				J5.setCellValue((String) propertyNgaySinh.getValue());
				final Cell K5 = row43.createCell(10);
				K5.setCellValue("");

				final Property propertyNgheNghiep = table.getContainerProperty(i, "NGHE_NGHIEP_NVP");
				final Cell L5 = row43.createCell(11);
				L5.setCellValue((String) propertyNgheNghiep.getValue());

				final Property propertylpt = table.getContainerProperty(i, "LOAI_PHUONG_TIEN");
				final Cell M5 = row43.createCell(12);
				M5.setCellValue((String) propertylpt.getValue());

				final Property propertyBKS = table.getContainerProperty(i, "BIEN_KIEM_SOAT");
				final Cell N5 = row43.createCell(13);
				N5.setCellValue((String) propertyBKS.getValue());

				final Property propertydiaDiemVPHC = table.getContainerProperty(i, "DIA_DIEM_VPHC");
				final Cell O5 = row43.createCell(14);
				O5.setCellValue((String) propertydiaDiemVPHC.getValue());

				final Property propertyNoiDungVphc = table.getContainerProperty(i, "NOI_DUNG_VPHC");
				final Cell P5 = row43.createCell(15);
				final int count = 0;
				String NoiDung = "";
				if (propertyNoiDungVphc.getValue() == null) {
					NoiDung = "";
				} else {
					NoiDung = propertyNoiDungVphc.getValue().toString().trim();
					if (NoiDung.contains("quy định tại") == true) {
						P5.setCellValue(substrFromNghiD(typefile(NoiDung)));
					} else {
						P5.setCellValue(NoiDung);
					}
				}

				String sbHvvp = "";
				final Cell Q5 = row43.createCell(16);
				String NoiDungHV = "";
				if (propertyNoiDungVphc.getValue() == null) {
					NoiDungHV = "";
				} else {
					NoiDungHV = propertyNoiDungVphc.getValue().toString().trim();
					if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) != 2
							&& Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) != 1) {
						if (NoiDungHV.contains("quy định tại") == true) {
							sbHvvp = SubStrLuat(NoiDungHV);
						} else {
							sbHvvp = NoiDungHV;
						}
					} else {
						sbHvvp = "";
					}
				}

				final Property propertyDieuLuat = table.getContainerProperty(i, "DIEU_LUAT");
				Q5.setCellValue((String) propertyDieuLuat.getValue());

				// final Cell R5 = row43.createCell(17);
				// R5.setCellValue(tenNhom);

				final Property propertyTvTg = table.getContainerProperty(i, "TANG_VAT_TG");
				String tangVat = "";
				if (propertyTvTg.getValue() == null) {
					tangVat = "";
				} else {
					tangVat = propertyTvTg.getValue().toString().trim();
					if (tangVat.contains("nơi cấp") == true) {
						substr(tangVat);
					} else {
						tangVat = "";
					}
				}

				final Cell S5 = row43.createCell(18);
				S5.setCellValue((String) propertyTvTg.getValue());

				final Property propertyHangGP = table.getContainerProperty(i, "HANG_GPLX");
				final Cell T5 = row43.createCell(19);
				if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) == 43) {
					T5.setCellValue((String) propertyHangGP.getValue());
				} else if (Long.parseLong(propertyLOAI_BB_QD.getValue().toString().trim()) != 43) {
					if (propertyTvTg.getValue() != null) {
						if (propertyTvTg.getValue().toString().contains("giấy phép lái xe")) {
							final String soGPLX = propertyTvTg.getValue().toString().split(" hạng ", 2)[1].trim();
							if (soGPLX.contains("số")) {
								final String hanggphep = soGPLX.split("số", 2)[0].trim();
								T5.setCellValue(hanggphep);
							}
						}
					}
				}

				final Property propertyGP = table.getContainerProperty(i, "GPLX");
				final Property propertyKD = table.getContainerProperty(i, "KIEM_DINH");
				final Cell U5 = row43.createCell(20);
				String gplx = "";
				String kiemdinh = "";
				final String dkyxe = "";
				String giayto = "";
				if (loaiBB == 43) {
					if (propertyGP.getValue() != null) {
						gplx = "Số GPLX: " + propertyGP.getValue().toString().trim();
					}
					if (propertyKD.getValue() != null) {
						kiemdinh = "Số giấy kiểm định: " + propertyKD.getValue().toString().trim();
					}
					giayto = (propertyGP.getValue() == null ? "" : (gplx + "; "))
							+ (propertyKD.getValue() == null ? "" : (kiemdinh + "; "));
				} else if (loaiBB != 43) {
					if (propertyTvTg.getValue() != null) {
						String gphep = "";
						String kiemDinh = "";
						String DkyXe = "";
						if (propertyTvTg.getValue().toString().contains("Khác")) {
							final String TangVat = propertyTvTg.getValue().toString().split("Khác", 2)[0];
							if (TangVat.contains("giấy phép lái xe")) {
								final String soGPLX = TangVat.split("số", 2)[1];
								if (soGPLX.contains(",")) {
									gphep = "Số GPLX: " + soGPLX.split(",", 2)[0].trim();
								} else if ((!soGPLX.contains(","))
										&& (soGPLX.contains("giá trị đến") && (!(soGPLX.contains("có giá trị đến"))))) {
									final int countGiaTri = soGPLX.split("giá trị đến").length;
									gphep = "Số GPLX: " + soGPLX.split("giá trị đến", countGiaTri)[0].trim();
								} else if ((!soGPLX.contains("giá trị đến")) && (soGPLX.contains("nơi cấp"))) {
									final int countGiaTri = soGPLX.split("nơi cấp").length;
									gphep = "Số GPLX: " + soGPLX.split("nơi cấp", countGiaTri)[0].trim();

								}
							}

							if (TangVat.contains("giấy đăng ký xe")) {
								final String Dki = TangVat.split("giấy đăng ký xe", 2)[1].trim();
								if (Dki.contains(":")) {
									final String soGPLX = Dki.split(":", 2)[1].trim();
									if (soGPLX.contains(",")) {
										DkyXe = "Số đăng ký xe: " + soGPLX.split(",", 2)[0].trim();
									} else if ((!soGPLX.contains(",")) && (soGPLX.contains("nơi cấp"))) {
										final int countGiaTri = soGPLX.split("nơi cấp").length;
										DkyXe = "Số đăng ký xe: " + soGPLX.split("nơi cấp", countGiaTri)[0].trim();

									}
								}
							}
							if (TangVat.contains("giấy chứng nhận kiểm định an toàn kỹ thuật và bảo vệ môi trường")) {
								final String Dki = TangVat
										.split("giấy chứng nhận kiểm định an toàn kỹ thuật và bảo vệ môi trường", 2)[1]
												.trim();
								if (Dki.contains(":")) {
									final int countDau = Dki.split(":").length;
									final String soGPLX = Dki.split(":", countDau)[1].trim();
									if (soGPLX.contains(",")) {
										kiemDinh = "Số giấy kiểm định: " + soGPLX.split(",", 2)[0].trim();
									} else if ((!soGPLX.contains(",")) && (soGPLX.contains("có giá trị đến"))) {
										kiemDinh = "Số giấy kiểm định: " + soGPLX.split("có giá trị đến", 2)[0].trim();
									} else if ((!soGPLX.contains("có giá trị đến")) && (soGPLX.contains(";"))) {
										final int countGiaTri = soGPLX.split(";").length;
										kiemDinh = "Số giấy kiểm định: " + soGPLX.split(";", countGiaTri)[0].trim();

									}
								}
							}
						} else {
							if (propertyTvTg.getValue().toString().contains("giấy phép lái xe")) {
								final String soGPLX = propertyTvTg.getValue().toString().split("số", 2)[1];
								if (soGPLX.contains(";")) {
									final int countDau = soGPLX.split(";").length;
									final String GiayPhep = soGPLX.split(";", countDau)[0].trim();
									if (GiayPhep.contains(",")) {
										gphep = "Số GPLX: " + GiayPhep.split(",", 2)[0].trim();
										final int countGiaTri = GiayPhep.split("giá trị đến").length;
										gphep = "Số GPLX: " + GiayPhep.split("giá trị đến", countGiaTri)[0].trim();
									} else if ((!soGPLX.contains("giá trị đến")) && (GiayPhep.contains("nơi cấp"))) {
										final int countGiaTri = GiayPhep.split("nơi cấp").length;
										gphep = "Số GPLX: " + GiayPhep.split("nơi cấp", countGiaTri)[0].trim();

									}
								}
							}

							if (propertyTvTg.getValue().toString().contains("giấy đăng ký xe")) {
								final String Dki = propertyTvTg.getValue().toString().split("giấy đăng ký xe", 2)[1]
										.trim();
								if (Dki.contains(":")) {
									final int countDau = Dki.split(":").length;
									final String soGPLX = Dki.split(":", countDau)[1].trim();
									if (soGPLX.contains(",")) {
										final int countDauP = soGPLX.split(",").length;
										DkyXe = "Số đăng ký xe: " + soGPLX.split(",", countDauP)[0].trim();
									} else if ((!soGPLX.contains(",")) && (soGPLX.contains("nơi cấp"))) {
										int countGiaTri = soGPLX.split("nơi cấp").length;
										if (countGiaTri == 1) {
											countGiaTri = 2;
										}
										DkyXe = "Số đăng ký xe: " + soGPLX.split("nơi cấp", countGiaTri)[0].trim();

									}
								}
							}
							if (propertyTvTg.getValue().toString()
									.contains("giấy chứng nhận kiểm định an toàn kỹ thuật và bảo vệ môi trường")) {
								final String Dki = propertyTvTg.getValue().toString()
										.split("giấy chứng nhận kiểm định an toàn kỹ thuật và bảo vệ môi trường", 2)[1]
												.trim();
								if (Dki.contains(":")) {
									final int countDau = Dki.split(":").length;
									final String soGPLX = Dki.split(":", countDau)[1].trim();
									if (soGPLX.contains(",")) {
										kiemDinh = "Số giấy kiểm định: " + soGPLX.split(",", 2)[0].trim();
									} else if ((!(soGPLX.contains(","))) && (soGPLX.contains("có giá trị đến"))) {
										kiemDinh = "Số giấy kiểm định: " + soGPLX.split("có giá trị đến", 2)[0].trim();
									} else if ((!soGPLX.contains("có giá trị đến")) && (soGPLX.contains(";"))) {
										final int countGiaTri = soGPLX.split(";").length;
										kiemDinh = "Số giấy kiểm định: " + soGPLX.split(";", countGiaTri)[0].trim();

									}
								}
							}
						}
						giayto = (gphep == "" ? "" : (gphep + "; ")) + (DkyXe == "" ? "" : (DkyXe + "; "))
								+ (kiemDinh == "" ? "" : (kiemDinh + "; "));
					}

				}
				U5.setCellValue(giayto.trim());

				final Cell V5 = row43.createCell(21);
				String finalValueGiatri = "";
				if (propertyTvTg.getValue() != null) {
					if (propertyTvTg.getValue().toString().contains("giá trị đến")
							&& (!propertyTvTg.getValue().toString().contains("có giá trị đến"))) {
						final int Dem = propertyTvTg.getValue().toString().split("giá trị đến").length;
						final String giatriDen = propertyTvTg.getValue().toString().split("giá trị đến", Dem)[1].trim();
						if (giatriDen.contains(";")) {
							int countSplit = giatriDen.split(";").length;
							if (countSplit == 1) {
								countSplit = 2;
							}
							final String giatri = giatriDen.split(";", countSplit)[0].trim();
							if (giatri.length() <= 10) {
								for (int j = giatri.length() - 1; j >= 0; j--) {
									if (giatri.charAt(j) != ';') {
										finalValueGiatri = giatri.charAt(j) + finalValueGiatri;
									} else {
										break;
									}
								}
							} else if (giatri.length() > 10) {
								finalValueGiatri = giatriDen.substring(0, 10).trim();
							}
						}
					} else if (propertyTvTg.getValue().toString().contains("có giá trị đến")) {
						final int Dem = propertyTvTg.getValue().toString().split("có giá trị đến").length;
						final String giatriDen = propertyTvTg.getValue().toString().split("có giá trị đến", Dem)[1]
								.trim();
						String GiaTriWithoutDau = "";
						if (giatriDen.contains(":")) {
							int count2Dots = giatriDen.split(":").length;
							if (count2Dots == 1) {
								count2Dots = 2;
							}
							GiaTriWithoutDau = giatriDen.split(":", count2Dots)[1].trim();
						} else if (!giatriDen.contains(":")) {
							GiaTriWithoutDau = giatriDen;
						}
						if (GiaTriWithoutDau.contains(";")) {
							final int countSplit = GiaTriWithoutDau.split(";").length;
							if (countSplit <= 1) {
								finalValueGiatri = GiaTriWithoutDau;
							} else if (countSplit > 1) {
								final String giatri = GiaTriWithoutDau.split(";", countSplit)[0];
								if (giatri.length() <= 10) {
									for (int j = giatri.length() - 1; j >= 0; j--) {
										if (giatri.charAt(j) != ';') {
											finalValueGiatri = giatri.charAt(j) + finalValueGiatri;
										} else {
											break;
										}
									}
								} else if (giatri.length() > 10) {
									finalValueGiatri = giatri.substring(0, 10).trim();
								}
							}
						}
					}
				}
				V5.setCellValue(finalValueGiatri);

				final Cell W5 = row43.createCell(22);
				if (propertyTvTg.getValue() != null) {
					W5.setCellValue(substr((String) propertyTvTg.getValue()));
				}

				final Property propertyThoiHanTg = table.getContainerProperty(i, "THOI_HAN_TG");
				final Cell X5 = row43.createCell(23);
				X5.setCellValue((String) propertyThoiHanTg.getValue());

				final Property propertyTrHTXP = table.getContainerProperty(i, "HINH_THUC_XP");
				final Cell Y5 = row43.createCell(24);
				Y5.setCellValue((String) propertyTrHTXP.getValue());

				final Property propertyTienNp = table.getContainerProperty(i, "TONG_MUC_PHAT");
				final Cell Z5 = row43.createCell(25);
				final NumberFormat fmMoney = NumberFormat.getCurrencyInstance(new Locale("vn", "VN"));
				final DecimalFormatSymbols formatSym = ((DecimalFormat) fmMoney).getDecimalFormatSymbols();
				formatSym.setCurrencySymbol("");
				((DecimalFormat) fmMoney).setDecimalFormatSymbols(formatSym);
				if (propertyTienNp.getValue() != null && propertyTienNp.getValue() != "") {
					Z5.setCellValue(
							fmMoney.format(Long.parseLong((String) propertyTienNp.getValue())).replace(",", "."));
				}

				final Property propertyTrThaiNp = table.getContainerProperty(i, "TRANG_THAI_NP");
				final Cell AA5 = row43.createCell(26);
				String trangThai = "";
				if (propertyTrThaiNp.getValue() != null && propertyTrThaiNp.getValue() != "") {
					if (Integer.parseInt(propertyTrThaiNp.getValue().toString().trim()) == 3) {
						trangThai = "Đã thanh toán qua DVC";
					} else if (Integer.parseInt(propertyTrThaiNp.getValue().toString().trim()) == 4) {
						trangThai = "Đã gửi SMS";
					} else if (Integer.parseInt(propertyTrThaiNp.getValue().toString().trim()) == 5) {
						trangThai = "Đã thanh toán trực tiếp";
					}
				}
				AA5.setCellValue(trangThai);

				final SimpleDateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");
				final Property propertyXpbs = table.getContainerProperty(i, "XU_PHAT_BO_SUNG");
				final Cell AB5 = row43.createCell(27);
				AB5.setCellValue((String) propertyXpbs.getValue());

				final Property PptuNgayXpbs = table.getContainerProperty(i, "TU_NGAY_XPBS");
				final Cell AC5 = row43.createCell(28);
				String FromDateXpbs = "";
				if (PptuNgayXpbs.getValue() != null && PptuNgayXpbs.getValue() != "") {
					FromDateXpbs = (String) PptuNgayXpbs.getValue();
					final DateFormat formatDateXpbs = new SimpleDateFormat("yyyy-MM-dd");
					Date dateXpbs;
					try {
						dateXpbs = formatDateXpbs.parse(FromDateXpbs);
						AC5.setCellValue(dfm.format(dateXpbs));
					} catch (final ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				final Property PpDenNgayXpbs = table.getContainerProperty(i, "DEN_NGAY_XPBS");
				final Cell AD5 = row43.createCell(29);
				String ToDateXpbs = "";
				if (PpDenNgayXpbs.getValue() != null && PpDenNgayXpbs.getValue() != "") {
					ToDateXpbs = (String) PpDenNgayXpbs.getValue();
					final DateFormat formatDateXpbs = new SimpleDateFormat("yyyy-MM-dd");
					Date dateXpbs;
					try {
						dateXpbs = formatDateXpbs.parse(ToDateXpbs);
						AD5.setCellValue(dfm.format(dateXpbs));
					} catch (final ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				final Property PpBPKP = table.getContainerProperty(i, "BIEN_PHAP_KHAC_PHUC");
				final Cell AE5 = row43.createCell(30);
				AE5.setCellValue((String) PpBPKP.getValue());

				final Property PpTvtl = table.getContainerProperty(i, "TANG_VAT_TRA_LAI");
				final Cell AT5 = row43.createCell(31);
				AT5.setCellValue((String) PpTvtl.getValue());

				final Property PpNgayLapBB = table.getContainerProperty(i, "NGAY_LAP_BB");
				final Cell AF5 = row43.createCell(32);
				Date newDate;
				if (PpNgayLapBB.getValue() != null) {
					newDate = (Date) PpNgayLapBB.getValue();
					AF5.setCellValue(dfm.format(newDate));
				}

				final Property PpTenDvLap = table.getContainerProperty(i, "TEN_DON_VI_LAP");
				final Cell AM5 = row43.createCell(33);
				AM5.setCellValue((String) PpTenDvLap.getValue());

				final Property PpTenDv = table.getContainerProperty(i, "TEN_DON_VI");
				final Cell AG5 = row43.createCell(34);
				AG5.setCellValue((String) PpTenDv.getValue());

				final Property PpTenCb = table.getContainerProperty(i, "TEN_CAN_BO");
				final Cell AH5 = row43.createCell(35);
				AH5.setCellValue((String) PpTenCb.getValue());

				final Property PpCbcv = table.getContainerProperty(i, "CAP_BAC_CHUC_VU");
				final Cell AI5 = row43.createCell(36);
				String chucvu = "";
				if (PpCbcv.getValue() != null) {
					if (PpCbcv.getValue().toString().trim().contains(",")) {
						chucvu = PpCbcv.getValue().toString().trim().split(",", 2)[1].trim();
					}
				}
				AI5.setCellValue(chucvu);
				final Cell AJ5 = row43.createCell(37);
				AJ5.setCellValue(PpTenCb.getValue().toString().trim());
				final Cell AK5 = row43.createCell(38);
				AK5.setCellValue(this.diaBanVp);

				final Property PpLinhVuc = table.getContainerProperty(i, "LINH_VUC_GIAO_THONG");
				final Cell AL5 = row43.createCell(39);
				String linhVucGt = "";
				if (PpLinhVuc.getValue() != "") {
					if (Integer.parseInt(PpLinhVuc.getValue().toString().trim()) == 1) {
						linhVucGt = "Đường bộ";
					} else if (Integer.parseInt(PpLinhVuc.getValue().toString().trim()) == 2) {
						linhVucGt = "Đường sắt";
					} else if (Integer.parseInt(PpLinhVuc.getValue().toString().trim()) == 4) {
						linhVucGt = "Đường thủy";
					}
				}
				AL5.setCellValue(linhVucGt);
			}

			try {
				String fromDate = "";
				if (this.pdfNgay.getValue() != null) {
					fromDate = FormatUtils.dateFormatddMMyyyy.format(this.pdfNgay.getValue());
				}
//				final Object[] arrayItem = this.resourceSet.toArray();
//				for (int i = 0; i < this.resourceSet.size(); i++) {
//					final String A = arrayItem[i].toString();
					final long donviID = Long.valueOf(String.valueOf(idDV));
					final DonViCanhsatGt dv = new DonViCanhsatGtDAO().find(donviID);
					final String maTrucThuoc = dv.getMaTrucThuoc();
					final String tenBC = dv.getTenVietTatDonViBc();
					final String ddhc  = dv.getDiaDanhHanhChinh().getTenVietTat();
					String Name = "";
					final String a = "";
					 File tempFile = new File("");
					if (maTrucThuoc.equals("G01")) {

					//	Name = "BCTH_C08_" + tenBC + fromDate.replace("/", "");
						Name =  tenBC +"_"+ fromDate.replace("/", "");
						tempFile = new File("BCTH_C08_"+Name +".xlsx");
					} else {
					//	Name = "BCTH_PC08_" +ddhc+"_"+ tenBC + fromDate.replace("/", "");
						Name =  ddhc+"_"+ tenBC +"_"+ fromDate.replace("/", "");
						tempFile = new File("BCTH_PC08_"+Name + ".xlsx");
					}

			//		final File tempFile = File.createTempFile(Name + ".xlsx", a);
			//		tempFile = File.createTempFile("Bao_Cao_TongHop.", ".xlsx");
					final FileOutputStream tempOutputStream = new FileOutputStream(tempFile);
					workbook.write(tempOutputStream);
					try (OutputStream os = tempOutputStream) {
						// workbook.write(os);

						final Resource resourceBaoCao = new FileResource(tempFile);
						Page.getCurrent().open(resourceBaoCao, "_blank", false);

					} catch (final IOException e) {
						e.printStackTrace();
					}
//				}
			} catch (final IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Done");
			}
		
		
		} else if (!this.pdfNgay.isEmpty() && (this.checkBox.getValue() == true) && CapDV == 1) {

			final Date all = this.pdfNgay.getValue();

			final Format formatter = new SimpleDateFormat("dd/MM/yyyy");
			final String s = formatter.format(all);
			final String NameBC = "BCTH_0_" + s.replace("/", "");
			final String ReNameBC = "BCTH_C08_" + s.replace("/", "");

			final String a[] = s.split("/");
			final String nam = a[a.length - 1];
			final String thang = a[a.length - 2];
			final String ngay = a[a.length - 3];

			String temporaryDirectory = "";
			if (SystemUtils.IS_OS_LINUX) {
				temporaryDirectory = System.getenv("REPORTING_PATH");
				temporaryDirectory = temporaryDirectory + "/daily/" + nam + "/" + thang + "/" + ngay + "/";
				System.out.print(temporaryDirectory);
			} else {
				temporaryDirectory = "D:/";
			}
		//	final File source = new File("//10.0.3.237/up/reporting/daily/" + nam + "/" + thang + "/" + ngay + "/" + NameBC + ".xlsx");
		//	final File fileRename = new File("//10.0.3.237/up/reporting/daily/" + nam + "/" + thang + "/" + ngay + "/" + ReNameBC + ".xlsx");

			 final File source = new File(temporaryDirectory +NameBC+".xlsx");
			// File (or directory) with new name
			 final File fileRename = new File(temporaryDirectory + ReNameBC+".xlsx");
			 final String path = source.getPath();
			 LayFileExcel(path, ReNameBC);
		
		}

//		else {
//			Notification.show("VUI LÒNG CHỌN NGÀY XUẤT BÁO CÁO");
//			this.pdfNgay.focus();
//			return;
//		}

	}

	/**
	 * Event handler delegate method for the {@link XdevButton} {@link #button}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void button_buttonClick(final Button.ClickEvent event) {
		ExcelExport excelExport;
		excelExport = new ExcelExport(this.table);
		excelExport.excludeCollapsedColumns();
		excelExport.setReportTitle("Đơn vị CSGT ");
		excelExport.setDisplayTotals(false);
		excelExport.setExportFileName("donViCSGT.xls");
		excelExport.export();
	}

	/*
	 * WARNING: Do NOT edit!<br>The content of this method is always regenerated by
	 * the UI designer.
	 */
	// <generated-code name="initUI">
	private void initUI() {
		this.gridLayout = new XdevGridLayout();
		this.gridLayout3 = new XdevGridLayout();
		this.gridLayout7 = new XdevGridLayout();
		this.horizontalLayout2 = new XdevHorizontalLayout();
		this.label5 = new XdevLabel();
		this.txtTimKiem = new XdevTextField();
		this.gridLayout20 = new XdevGridLayout();
		this.checkBox = new XdevCheckBox();
		this.label4 = new XdevLabel();
		this.treeTable = new XdevTreeTable();
		this.tabSheet = new XdevTabSheet();
		this.gridLayout10 = new XdevGridLayout();
		this.gridLayout11 = new XdevGridLayout();
		this.horizontalLayout11 = new XdevHorizontalLayout();
		this.gridLayout31 = new XdevGridLayout();
		this.label58 = new XdevLabel();
		this.label35 = new XdevLabel();
		this.gridLayout32 = new XdevGridLayout();
		this.pdfNgay = new XdevPopupDateField();
		this.gridLayout33 = new XdevGridLayout();
		this.btnWeeklyReport3 = new XdevButton();
		this.horizontalLayout10 = new XdevHorizontalLayout();
		this.gridLayout12 = new XdevGridLayout();
		this.label34 = new XdevLabel();
		this.label59 = new XdevLabel();
		this.gridLayout21 = new XdevGridLayout();
		this.cmbTuan = new XdevComboBox<>();
		this.gridLayout13 = new XdevGridLayout();
		this.btnWeeklyReport = new XdevButton();
		this.horizontalLayout7 = new XdevHorizontalLayout();
		this.gridLayout14 = new XdevGridLayout();
		this.label55 = new XdevLabel();
		this.label60 = new XdevLabel();
		this.gridLayout22 = new XdevGridLayout();
		this.cmbThang = new XdevComboBox<>();
		this.comboBox = new XdevComboBox<>();
		this.gridLayout15 = new XdevGridLayout();
		this.btnMonthlyReport = new XdevButton();
		this.horizontalLayout8 = new XdevHorizontalLayout();
		this.gridLayout16 = new XdevGridLayout();
		this.label54 = new XdevLabel();
		this.label61 = new XdevLabel();
		this.gridLayout23 = new XdevGridLayout();
		this.cmbQuy = new XdevComboBox<>();
		this.cmbTenQuy = new XdevComboBox<>();
		this.gridLayout17 = new XdevGridLayout();
		this.btnQuarterReport = new XdevButton();
		this.horizontalLayout9 = new XdevHorizontalLayout();
		this.gridLayout25 = new XdevGridLayout();
		this.label57 = new XdevLabel();
		this.label62 = new XdevLabel();
		this.gridLayout24 = new XdevGridLayout();
		this.cmbQuy2 = new XdevComboBox<>();
		this.gridLayout26 = new XdevGridLayout();
		this.btnYearlyReport = new XdevButton();
		this.gridLayout9 = new XdevGridLayout();
		this.gridLayout8 = new XdevGridLayout();
		this.gridLayout2 = new XdevGridLayout();
		this.browserFrame = new XdevBrowserFrame();
		this.btnbaocaoTongHop = new XdevButton();
		this.btnTheoTieuChi = new XdevButton();
		this.gridLayout18 = new XdevGridLayout();
		this.gridLayout4 = new XdevGridLayout();
		this.label2 = new XdevLabel();
		this.label3 = new XdevLabel();
		this.gridLayout6 = new XdevGridLayout();
		this.FromDate = new XdevPopupDateField();
		this.label56 = new XdevLabel();
		this.ToDate = new XdevPopupDateField();
		this.label6 = new XdevLabel();
		this.cmbLinhVuc = new XdevComboBox<>();
		this.label31 = new XdevLabel();
		this.label37 = new XdevLabel();
		this.cmbToChuc = new XdevComboBox<>();
		this.label10 = new XdevLabel();
		this.txtTenNvp = new XdevTextField();
		this.label11 = new XdevLabel();
		this.txtDiaChiNvp = new XdevTextField();
		this.label36 = new XdevLabel();
		this.cmbNgheNghiep = new XdevComboBox<>();
		this.label52 = new XdevLabel();
		this.horizontalLayout5 = new XdevHorizontalLayout();
		this.txtTuoiTu = new XdevTextField();
		this.label53 = new XdevLabel();
		this.txtTuoiDen = new XdevTextField();
		this.label33 = new XdevLabel();
		this.horizontalLayout6 = new XdevHorizontalLayout();
		this.txtNamSinhTu = new XdevTextField();
		this.label32 = new XdevLabel();
		this.txtNamSinhDen = new XdevTextField();
		this.label28 = new XdevLabel();
		this.cmbLoaiGiayTo = new XdevComboBox<>();
		this.label = new XdevLabel();
		this.cmbNoiCapTv = new XdevComboBox<>();
		this.label23 = new XdevLabel();
		this.txtSoGiayTo = new XdevTextField();
		this.label38 = new XdevLabel();
		this.cmbHangGplx = new XdevComboBox<>();
		this.label24 = new XdevLabel();
		this.label25 = new XdevLabel();
		this.cmbLoaiPt = new XdevComboBox<>();
		this.label39 = new XdevLabel();
		this.txtBKS = new XdevTextField();
		this.label40 = new XdevLabel();
		this.label41 = new XdevLabel();
		this.horizontalLayout3 = new XdevHorizontalLayout();
		this.dateNgayTamGiuTu = new XdevPopupDateField();
		this.label42 = new XdevLabel();
		this.dateNgayTamGiuDen = new XdevPopupDateField();
		this.label12 = new XdevLabel();
		this.cmbCapPheDuyet = new XdevComboBox<>();
		this.label50 = new XdevLabel();
		this.cmbTrangThaiXl = new XdevComboBox<>();
		this.gridLayout5 = new XdevGridLayout();
		this.label43 = new XdevLabel();
		this.label44 = new XdevLabel();
		this.cmbTinh = new XdevComboBox<>();
		this.label45 = new XdevLabel();
		this.cmbQuanHuyen = new XdevComboBox<>();
		this.label46 = new XdevLabel();
		this.cmbPhuongXa = new XdevComboBox<>();
		this.label47 = new XdevLabel();
		this.cmbQuocLo = new XdevComboBox<>();
		this.label48 = new XdevLabel();
		this.cmbTuyenDuong = new XdevComboBox<>();
		this.label13 = new XdevLabel();
		this.label14 = new XdevLabel();
		this.cmbHinhThucPhat = new XdevComboBox<>();
		this.label22 = new XdevLabel();
		this.horizontalLayout4 = new XdevHorizontalLayout();
		this.txtPhatTienTu = new XdevTextField();
		this.label21 = new XdevLabel();
		this.txtPhatTienDen = new XdevTextField();
		this.label20 = new XdevLabel();
		this.cmbHinhThucNP = new XdevComboBox<>();
		this.label19 = new XdevLabel();
		this.cmbNopTrucTuyenQua = new XdevComboBox<>();
		this.label18 = new XdevLabel();
		this.label17 = new XdevLabel();
		this.cmbXpbs = new XdevComboBox<>();
		this.label15 = new XdevLabel();
		this.horizontalLayout = new XdevHorizontalLayout();
		this.pdFTuocTuNgay = new XdevPopupDateField();
		this.label16 = new XdevLabel();
		this.pdFTuocDenNgay = new XdevPopupDateField();
		this.label27 = new XdevLabel();
		this.label8 = new XdevLabel();
		this.cmbNhomHv = new XdevComboBox<>();
		this.label9 = new XdevLabel();
		this.cmbnhomHvvp = new XdevComboBox<>();
		this.label7 = new XdevLabel();
		this.cmbNghiDinh = new XdevComboBox<>();
		this.label26 = new XdevLabel();
		this.cmbHvvp = new XdevComboBox<>();
		this.label30 = new XdevLabel();
		this.label49 = new XdevLabel();
		this.cmbLoaiBb = new XdevComboBox<>();
		this.label29 = new XdevLabel();
		this.txtSoBb = new XdevTextField();
		this.label51 = new XdevLabel();
		this.cmbCanBoLap = new XdevComboBox<>();
		this.gridLayout19 = new XdevGridLayout();
		this.button = new XdevButton();
		this.table = new XdevTable<>();
		this.browserFrame2 = new XdevBrowserFrame();
	
		this.gridLayout.setStyleName("Grid_background");
		this.gridLayout3.setMargin(new MarginInfo(false, true, false, true));
		this.gridLayout7.setMargin(new MarginInfo(false));
		this.horizontalLayout2.setMargin(new MarginInfo(false));
		this.label5.setStyleName("label-baocao");
		this.label5.setValue("<b style=\"color: #779ecb; font-size: 1.2em;\">ĐƠN VỊ LẬP</b>");
		this.label5.setContentMode(ContentMode.HTML);
		this.txtTimKiem.setInputPrompt("Tìm kiếm đơn vị");
		this.txtTimKiem.setEnabled(false);
		this.txtTimKiem.setVisible(false);
		this.txtTimKiem.addShortcutListener(new AbstractField.FocusShortcut(this.txtTimKiem, ShortcutAction.KeyCode.ENTER));
		this.gridLayout20.setMargin(new MarginInfo(false));
		this.checkBox.setCaption("");
		this.label4.setValue("Chọn tất cả/Bỏ chọn");
		this.treeTable.setPageLength(20);
		this.tabSheet.setStyleName("framed");
		this.gridLayout11.setMargin(new MarginInfo(true, false, true, true));
		this.horizontalLayout11.setMargin(new MarginInfo(false));
		this.gridLayout31.setMargin(new MarginInfo(false));
		this.label58.setStyleName("content-bold");
		this.label58.setValue("BÁO CÁO THỐNG KÊ LƯỢT VI PHẠM NGÀY");
		this.label35.setValue("<i>(Dữ liệu được tính từ 00:00 đến 23:59)");
		this.label35.setContentMode(ContentMode.HTML);
		this.gridLayout32.setMargin(new MarginInfo(false));
		this.pdfNgay.setDateFormat("dd/MM/yyyy");
		this.gridLayout33.setMargin(new MarginInfo(false));
		this.btnWeeklyReport3.setIcon(FontAwesome.DOWNLOAD);
		this.btnWeeklyReport3.setCaption("XUẤT FILE EXCEL");
		this.btnWeeklyReport3.setPrimaryStyleName("v-btnRaqd");
		this.horizontalLayout10.setMargin(new MarginInfo(false));
		this.gridLayout12.setMargin(new MarginInfo(false));
		this.label34.setStyleName("content-bold");
		this.label34.setValue("BÁO CÁO TỔNG HỢP VI PHẠM HÀNH CHÍNH THEO TUẦN");
		this.label59.setValue("<i>(Dữ liệu được tính từ thứ 5 tuần trước đến thứ 4 tuần liền kề sau)");
		this.label59.setContentMode(ContentMode.HTML);
		this.gridLayout21.setMargin(new MarginInfo(false));
		this.gridLayout13.setMargin(new MarginInfo(false));
		this.btnWeeklyReport.setIcon(FontAwesome.DOWNLOAD);
		this.btnWeeklyReport.setCaption("XUẤT FILE EXCEL");
		this.btnWeeklyReport.setPrimaryStyleName("v-btnRaqd");
		this.horizontalLayout7.setMargin(new MarginInfo(false));
		this.gridLayout14.setMargin(new MarginInfo(false));
		this.label55.setStyleName("content-bold");
		this.label55.setValue("BÁO CÁO TỔNG HỢP VI PHẠM HÀNH CHÍNH THEO THÁNG");
		this.label60.setValue("<i>(Dữ liệu được tính từ 15 của tháng trước đến ngày 14 của tháng <br>liền kề sau)");
		this.label60.setContentMode(ContentMode.HTML);
		this.gridLayout22.setMargin(new MarginInfo(false));
		this.gridLayout15.setMargin(new MarginInfo(false));
		this.btnMonthlyReport.setIcon(FontAwesome.DOWNLOAD);
		this.btnMonthlyReport.setCaption("XUẤT FILE EXCEL");
		this.btnMonthlyReport.setPrimaryStyleName("v-btnRaqd");
		this.horizontalLayout8.setMargin(new MarginInfo(false));
		this.gridLayout16.setMargin(new MarginInfo(false));
		this.label54.setStyleName("content-bold");
		this.label54.setValue("BÁO CÁO TỔNG HỢP VI PHẠM HÀNH CHÍNH THEO QUÝ");
		this.label61.setValue(
				"<i>(Dữ liệu được tính từ ngày 15 của tháng đầu tiên của quý đến ngày 14 <br>của tháng cuối cùng của quý)");
		this.label61.setContentMode(ContentMode.HTML);
		this.gridLayout23.setMargin(new MarginInfo(false));
		this.gridLayout17.setMargin(new MarginInfo(false));
		this.btnQuarterReport.setIcon(FontAwesome.DOWNLOAD);
		this.btnQuarterReport.setCaption("XUẤT FILE EXCEL");
		this.btnQuarterReport.setPrimaryStyleName("v-btnRaqd");
		this.horizontalLayout9.setMargin(new MarginInfo(false));
		this.gridLayout25.setMargin(new MarginInfo(false));
		this.label57.setStyleName("content-bold");
		this.label57.setValue("BÁO CÁO TỔNG HỢP VI PHẠM HÀNH CHÍNH THEO NĂM");
		this.label62.setValue("<i>(Dữ liệu được tính từ ngày 15/12 năm trước đến ngày 15/12 năm sau)");
		this.label62.setContentMode(ContentMode.HTML);
		this.gridLayout24.setMargin(new MarginInfo(false));
		this.gridLayout26.setMargin(new MarginInfo(false));
		this.btnYearlyReport.setIcon(FontAwesome.DOWNLOAD);
		this.btnYearlyReport.setCaption("XUẤT FILE EXCEL");
		this.btnYearlyReport.setPrimaryStyleName("v-btnRaqd");
		this.gridLayout9.setMargin(new MarginInfo(false));
		this.gridLayout8.setMargin(new MarginInfo(true, true, false, false));
		this.btnbaocaoTongHop.setIcon(FontAwesome.DOWNLOAD);
		this.btnbaocaoTongHop.setCaption("TỔNG HỢP");
		this.btnbaocaoTongHop.setPrimaryStyleName("v-btnin1");
		this.btnTheoTieuChi.setIcon(FontAwesome.DOWNLOAD);
		this.btnTheoTieuChi.setCaption("THEO TIÊU CHÍ");
		this.btnTheoTieuChi.setPrimaryStyleName("v-btnin1");
		this.gridLayout18.setMargin(new MarginInfo(false));
		this.gridLayout4.setMargin(new MarginInfo(false, true, true, true));
		this.label2.setStyleName("label-baocao");
		this.label2.setValue("<font color = '#17a2c8'><b>THỜI GIAN LẬP </b></font>");
		this.label2.setContentMode(ContentMode.HTML);
		this.label3.setValue("Từ ngày");
		this.gridLayout6.setSpacing(false);
		this.gridLayout6.setMargin(new MarginInfo(false));
		this.label56.setValue("Đến ngày");
		this.label6.setStyleName("label-baocao");
		this.label6.setValue("<font color = '#17a2c8'><b>LĨNH VỰC");
		this.label6.setContentMode(ContentMode.HTML);
		this.cmbLinhVuc.setFilteringMode(FilteringMode.CONTAINS);
		this.cmbLinhVuc.setNullSelectionAllowed(false);
		this.label31.setStyleName("label-baocao");
		this.label31.setValue("<font color = '#17a2c8'><b>ĐỐI TƯỢNG VI PHẠM");
		this.label31.setContentMode(ContentMode.HTML);
		this.label37.setValue("Cá nhân/Tổ chức");
		this.cmbToChuc.setFilteringMode(FilteringMode.CONTAINS);
		this.label10.setValue("Tên");
		this.label11.setValue("Địa chỉ");
		this.label36.setValue("Nghề nghiệp");
		this.cmbNgheNghiep.setItemCaptionFromAnnotation(false);
		this.cmbNgheNghiep.setFilteringMode(FilteringMode.CONTAINS);
		this.cmbNgheNghiep.setInputPrompt("Tất cả");
		this.cmbNgheNghiep.setContainerDataSource(NgheNghiep.class);
		this.cmbNgheNghiep.setItemCaptionPropertyId(NgheNghiep_.tenNgheNghiep.getName());
		this.label52.setValue("Độ tuổi (từ)");
		this.horizontalLayout5.setMargin(new MarginInfo(false));
		this.label53.setValue("(đến)");
		this.label33.setValue("Năm sinh (từ)");
		this.horizontalLayout6.setMargin(new MarginInfo(false));
		this.label32.setValue("(đến)");
		this.label28.setStyleName("label-baocao");
		this.label28.setValue("<font color = '#17a2c8'><b>TANG VẬT");
		this.label28.setContentMode(ContentMode.HTML);
		this.cmbLoaiGiayTo.setFilteringMode(FilteringMode.CONTAINS);
		this.label.setValue("Nơi cấp");
		this.cmbNoiCapTv.setItemCaptionFromAnnotation(false);
		this.cmbNoiCapTv.setFilteringMode(FilteringMode.CONTAINS);
		this.cmbNoiCapTv.setPageLength(50);
		this.cmbNoiCapTv.setInputPrompt("Tất cả");
		this.cmbNoiCapTv.setContainerDataSource(NoicapGiayto.class);
		this.cmbNoiCapTv.setItemCaptionPropertyId(NoicapGiayto_.ten.getName());
		this.label23.setValue("Số giấy tờ");
		this.label38.setValue("Hạng GPLX");
		this.cmbHangGplx.setItemCaptionFromAnnotation(false);
		this.cmbHangGplx.setFilteringMode(FilteringMode.CONTAINS);
		this.cmbHangGplx.setInputPrompt("Tất cả");
		this.cmbHangGplx.setContainerDataSource(HangGplx.class);
		this.cmbHangGplx.setItemCaptionPropertyId(HangGplx_.tenHang.getName());
		this.label24.setStyleName("label-baocao");
		this.label24.setValue("<font color = '#17a2c8'><b>PHƯƠNG TIỆN VI PHẠM</b></font>");
		this.label24.setContentMode(ContentMode.HTML);
		this.label25.setValue("Loại phương tiện");
		this.cmbLoaiPt.setItemCaptionFromAnnotation(false);
		this.cmbLoaiPt.setFilteringMode(FilteringMode.CONTAINS);
		this.cmbLoaiPt.setPageLength(15);
		this.cmbLoaiPt.setInputPrompt("Tất cả");
		this.cmbLoaiPt.setContainerDataSource(LoaiPhuongTien.class);
		this.cmbLoaiPt.setItemCaptionPropertyId(LoaiPhuongTien_.loaiPt.getName());
		this.label39.setValue("Biển số");
		this.label40.setValue("<font color = '#17a2c8'><b>NGÀY TẠM GIỮ</b></font>");
		this.label40.setContentMode(ContentMode.HTML);
		this.label41.setValue("Từ ngày");
		this.horizontalLayout3.setMargin(new MarginInfo(false));
		this.dateNgayTamGiuTu.setDateFormat("dd/MM/yyyy");
		this.label42.setValue("Đến ngày");
		this.dateNgayTamGiuDen.setDateFormat("dd/MM/yyyy");
		this.label12.setStyleName("label-baocao");
		this.label12.setValue("<font color = '#17a2c8'><b>CẤP PHÊ DUYỆT");
		this.label12.setContentMode(ContentMode.HTML);
		this.cmbCapPheDuyet.setItemCaptionFromAnnotation(false);
		this.cmbCapPheDuyet.setFilteringMode(FilteringMode.CONTAINS);
		this.cmbCapPheDuyet.setInputPrompt("Tất cả");
		this.cmbCapPheDuyet.setContainerDataSource(ChucVu.class);
		this.cmbCapPheDuyet.setItemCaptionPropertyId(ChucVu_.tenChucVu.getName());
		this.label50.setValue("<font color = '#17a2c8'><b>TRẠNG THÁI XỬ LÝ");
		this.label50.setContentMode(ContentMode.HTML);
		this.cmbTrangThaiXl.setFilteringMode(FilteringMode.CONTAINS);
		this.gridLayout5.setMargin(new MarginInfo(false, true, true, true));
		this.label43.setValue("<font color = '#17a2c8'><b>ĐỊA DANH HÀNH CHÍNH");
		this.label43.setContentMode(ContentMode.HTML);
		this.label44.setValue("Tỉnh/Thành phố");
		this.cmbTinh.setItemCaptionFromAnnotation(false);
		this.cmbTinh.setFilteringMode(FilteringMode.CONTAINS);
		this.cmbTinh.setPageLength(50);
		this.cmbTinh.setInputPrompt("Tất cả");
		this.cmbTinh.setContainerDataSource(DiaDanhHanhChinh.class);
		this.cmbTinh.setItemCaptionPropertyId(DiaDanhHanhChinh_.ten.getName());
		this.label45.setValue("Quận/Huyện");
		this.cmbQuanHuyen.setItemCaptionFromAnnotation(false);
		this.cmbQuanHuyen.setFilteringMode(FilteringMode.CONTAINS);
		this.cmbQuanHuyen.setPageLength(50);
		this.cmbQuanHuyen.setInputPrompt("Tất cả");
		this.cmbQuanHuyen.setContainerDataSource(DiaDanhHanhChinh.class);
		this.cmbQuanHuyen.setItemCaptionPropertyId(DiaDanhHanhChinh_.ten.getName());
		this.label46.setValue("Phường/Xã");
		this.cmbPhuongXa.setItemCaptionFromAnnotation(false);
		this.cmbPhuongXa.setFilteringMode(FilteringMode.CONTAINS);
		this.cmbPhuongXa.setPageLength(100);
		this.cmbPhuongXa.setInputPrompt("Tất cả");
		this.cmbPhuongXa.setContainerDataSource(DiaDanhHanhChinh.class);
		this.cmbPhuongXa.setItemCaptionPropertyId(DiaDanhHanhChinh_.ten.getName());
		this.label47.setValue("Quốc lộ");
		this.cmbQuocLo.setItemCaptionFromAnnotation(false);
		this.cmbQuocLo.setFilteringMode(FilteringMode.CONTAINS);
		this.cmbQuocLo.setPageLength(100);
		this.cmbQuocLo.setInputPrompt("Tất cả");
		this.cmbQuocLo.setContainerDataSource(QuocLoTuyenduong.class,
				DAOs.get(QuocLoTuyenduongDAO.class).ListPoolByIdQuocLo());
		this.cmbQuocLo.setItemCaptionPropertyId(QuocLoTuyenduong_.tenDuong.getName());
		this.label48.setValue("Tuyến đường");
		this.cmbTuyenDuong.setItemCaptionFromAnnotation(false);
		this.cmbTuyenDuong.setFilteringMode(FilteringMode.CONTAINS);
		this.cmbTuyenDuong.setPageLength(100);
		this.cmbTuyenDuong.setInputPrompt("Tất cả");
		this.cmbTuyenDuong.setContainerDataSource(QuocLoTuyenduong.class,
				DAOs.get(QuocLoTuyenduongDAO.class).ListPoolByIdTuyenduong());
		this.cmbTuyenDuong.setItemCaptionPropertyId(QuocLoTuyenduong_.tenDuong.getName());
		this.label13.setStyleName("label-baocao");
		this.label13.setValue("<font color = '#17a2c8'><b>HÌNH THỨC XỬ LÝ");
		this.label13.setContentMode(ContentMode.HTML);
		this.label14.setValue("Hình thức phạt");
		this.cmbHinhThucPhat.setItemCaptionFromAnnotation(false);
		this.cmbHinhThucPhat.setFilteringMode(FilteringMode.CONTAINS);
		this.cmbHinhThucPhat.setInputPrompt("Tất cả");
		this.cmbHinhThucPhat.setContainerDataSource(HinhThucXuPhatVphc.class);
		this.cmbHinhThucPhat.setItemCaptionPropertyId(HinhThucXuPhatVphc_.tenHinhThuc.getName());
		this.label22.setValue("Phạt tiền (từ)");
		this.horizontalLayout4.setMargin(new MarginInfo(false));
		this.label21.setValue(" (đến)");
		this.label20.setValue("Hình thức nộp phạt");
		this.cmbHinhThucNP.setFilteringMode(FilteringMode.CONTAINS);
		this.label19.setValue("Nộp trực tuyến qua");
		this.cmbNopTrucTuyenQua.setFilteringMode(FilteringMode.CONTAINS);
		this.cmbNopTrucTuyenQua.setPageLength(15);
		this.cmbNopTrucTuyenQua.setInputPrompt("Tất cả");
		this.cmbNopTrucTuyenQua.setContainerDataSource(KhoBacNganHang.class);
		this.label18.setStyleName("label-baocao");
		this.label18.setValue("<font color = '#17a2c8'><b>HÌNH THỨC XPBS</b></font>");
		this.label18.setContentMode(ContentMode.HTML);
		this.label17.setValue("Hình thức phạt");
		this.cmbXpbs.setItemCaptionFromAnnotation(false);
		this.cmbXpbs.setFilteringMode(FilteringMode.CONTAINS);
		this.cmbXpbs.setInputPrompt("Tất cả");
		this.cmbXpbs.setContainerDataSource(HinhThucXuPhatBoSung.class,
				DAOs.get(HinhThucXuPhatBoSungDAO.class).ListHinHThucXPBS());
		this.cmbXpbs.setItemCaptionPropertyId(HinhThucXuPhatBoSung_.ghiChu.getName());
		this.label15.setValue("Ngày tước (từ)");
		this.horizontalLayout.setMargin(new MarginInfo(false));
		this.pdFTuocTuNgay.setDateFormat("dd/MM/yyyy");
		this.label16.setValue("(đến)");
		this.pdFTuocDenNgay.setDateFormat("dd/MM/yyyy");
		this.label27.setStyleName("label-baocao");
		this.label27.setValue("<font color = '#17a2c8'><b>HÀNH VI VI PHẠM");
		this.label27.setContentMode(ContentMode.HTML);
		this.label8.setValue("Nhóm HVVP");
		this.cmbNhomHv.setFilteringMode(FilteringMode.CONTAINS);
		this.cmbNhomHv.setInputPrompt("");
		this.label9.setValue("Nhóm HVVP");
		this.label9.setVisible(false);
		this.cmbnhomHvvp.setItemCaptionFromAnnotation(false);
		this.cmbnhomHvvp.setFilteringMode(FilteringMode.CONTAINS);
		this.cmbnhomHvvp.setVisible(false);
		this.cmbnhomHvvp.setPageLength(50);
		this.cmbnhomHvvp.setInputPrompt("Tất cả");
		this.cmbnhomHvvp.setEnabled(false);
		this.cmbnhomHvvp.setContainerDataSource(NhomHvvp.class);
		this.cmbnhomHvvp.setItemCaptionPropertyId(NhomHvvp_.ten.getName());
		this.label7.setValue("Nghị định");
		this.cmbNghiDinh.setItemCaptionFromAnnotation(false);
		this.cmbNghiDinh.setFilteringMode(FilteringMode.CONTAINS);
		this.cmbNghiDinh.setPageLength(50);
		this.cmbNghiDinh.setInputPrompt("Tất cả");
		this.cmbNghiDinh.setContainerDataSource(NghiDinhCp.class, DAOs.get(NghiDinhCpDAO.class).GetNghiDinh22());
		this.cmbNghiDinh.setItemCaptionPropertyId(NghiDinhCp_.ma.getName());
		this.label26.setValue("Hành vi vi phạm");
		this.cmbHvvp.setItemCaptionFromAnnotation(false);
		this.cmbHvvp.setFilteringMode(FilteringMode.CONTAINS);
		this.cmbHvvp.setPageLength(50);
		this.cmbHvvp.setInputPrompt("Tất cả");
		this.cmbHvvp.setContainerDataSource(HanhViViPham.class);
		this.cmbHvvp.setItemCaptionPropertyId(HanhViViPham_.tomTat.getName());
		this.label30.setStyleName("label-baocao");
		this.label30.setValue("<font color = '#17a2c8'><b>BIỂU MẪU");
		this.label30.setContentMode(ContentMode.HTML);
		this.label49.setValue("Loại BB/QĐ");
		this.cmbLoaiBb.setFilteringMode(FilteringMode.CONTAINS);
		this.label29.setValue("Số BB/QĐ");
		this.label51.setValue("Cán bộ lập");
		this.cmbCanBoLap.setItemCaptionFromAnnotation(false);
		this.cmbCanBoLap.setFilteringMode(FilteringMode.CONTAINS);
		this.cmbCanBoLap.setPageLength(100);
		this.cmbCanBoLap.setInputPrompt("Tất cả");
		this.cmbCanBoLap.setContainerDataSource(AuthUser.class);
		this.cmbCanBoLap.setItemCaptionPropertyId(AuthUser_.userName.getName());
		this.button.setCaption("Button");
		this.table.setContainerDataSource(BaoCaoTongHop.class, false);
		this.table.addGeneratedColumn("generated", new CreateID());
		this.table.setVisibleColumns("generated", BaoCaoTongHop_.maRutgon.getName(), "tenBBQd",
				BaoCaoTongHop_.soBienBan.getName(), BaoCaoTongHop_.toChuc.getName());
		this.table.setColumnHeader("generated", "STT");
		this.table.setColumnHeader("maRutgon", "Mã vụ việc");
		this.table.setColumnHeader("tenBBQd", "Loại BB/QĐ");
		this.table.setColumnHeader("soBienBan", "Số BB/QĐ");
		this.table.setColumnHeader("toChuc", "Tổ Chức");
	
		this.label5.setSizeUndefined();
		this.horizontalLayout2.addComponent(this.label5);
		this.txtTimKiem.setWidth(100, Unit.PERCENTAGE);
		this.txtTimKiem.setHeight(-1, Unit.PIXELS);
		this.horizontalLayout2.addComponent(this.txtTimKiem);
		this.horizontalLayout2.setExpandRatio(this.txtTimKiem, 10.0F);
		this.gridLayout20.setColumns(3);
		this.gridLayout20.setRows(2);
		this.checkBox.setSizeUndefined();
		this.gridLayout20.addComponent(this.checkBox, 0, 0);
		this.label4.setSizeUndefined();
		this.gridLayout20.addComponent(this.label4, 1, 0);
		final CustomComponent gridLayout20_hSpacer = new CustomComponent();
		gridLayout20_hSpacer.setSizeFull();
		this.gridLayout20.addComponent(gridLayout20_hSpacer, 2, 0, 2, 0);
		this.gridLayout20.setColumnExpandRatio(2, 1.0F);
		final CustomComponent gridLayout20_vSpacer = new CustomComponent();
		gridLayout20_vSpacer.setSizeFull();
		this.gridLayout20.addComponent(gridLayout20_vSpacer, 0, 1, 1, 1);
		this.gridLayout20.setRowExpandRatio(1, 1.0F);
		this.gridLayout7.setColumns(1);
		this.gridLayout7.setRows(3);
		this.horizontalLayout2.setWidth(100, Unit.PERCENTAGE);
		this.horizontalLayout2.setHeight(-1, Unit.PIXELS);
		this.gridLayout7.addComponent(this.horizontalLayout2, 0, 0);
		this.gridLayout20.setWidth(100, Unit.PERCENTAGE);
		this.gridLayout20.setHeight(30, Unit.PIXELS);
		this.gridLayout7.addComponent(this.gridLayout20, 0, 1);
		this.treeTable.setSizeFull();
		this.gridLayout7.addComponent(this.treeTable, 0, 2);
		this.gridLayout7.setColumnExpandRatio(0, 10.0F);
		this.gridLayout7.setRowExpandRatio(2, 10.0F);
		this.gridLayout31.setColumns(2);
		this.gridLayout31.setRows(3);
		this.label58.setSizeUndefined();
		this.gridLayout31.addComponent(this.label58, 0, 0);
		this.label35.setSizeUndefined();
		this.gridLayout31.addComponent(this.label35, 0, 1);
		final CustomComponent gridLayout31_hSpacer = new CustomComponent();
		gridLayout31_hSpacer.setSizeFull();
		this.gridLayout31.addComponent(gridLayout31_hSpacer, 1, 0, 1, 1);
		this.gridLayout31.setColumnExpandRatio(1, 1.0F);
		final CustomComponent gridLayout31_vSpacer = new CustomComponent();
		gridLayout31_vSpacer.setSizeFull();
		this.gridLayout31.addComponent(gridLayout31_vSpacer, 0, 2, 0, 2);
		this.gridLayout31.setRowExpandRatio(2, 1.0F);
		this.gridLayout32.setColumns(2);
		this.gridLayout32.setRows(2);
		this.pdfNgay.setSizeUndefined();
		this.gridLayout32.addComponent(this.pdfNgay, 0, 0);
		final CustomComponent gridLayout32_hSpacer = new CustomComponent();
		gridLayout32_hSpacer.setSizeFull();
		this.gridLayout32.addComponent(gridLayout32_hSpacer, 1, 0, 1, 0);
		this.gridLayout32.setColumnExpandRatio(1, 1.0F);
		final CustomComponent gridLayout32_vSpacer = new CustomComponent();
		gridLayout32_vSpacer.setSizeFull();
		this.gridLayout32.addComponent(gridLayout32_vSpacer, 0, 1, 0, 1);
		this.gridLayout32.setRowExpandRatio(1, 1.0F);
		this.gridLayout33.setColumns(1);
		this.gridLayout33.setRows(2);
		this.btnWeeklyReport3.setWidth(160, Unit.PIXELS);
		this.btnWeeklyReport3.setHeight(-1, Unit.PIXELS);
		this.gridLayout33.addComponent(this.btnWeeklyReport3, 0, 0);
		this.gridLayout33.setComponentAlignment(this.btnWeeklyReport3, Alignment.TOP_RIGHT);
		this.gridLayout33.setColumnExpandRatio(0, 10.0F);
		final CustomComponent gridLayout33_vSpacer = new CustomComponent();
		gridLayout33_vSpacer.setSizeFull();
		this.gridLayout33.addComponent(gridLayout33_vSpacer, 0, 1, 0, 1);
		this.gridLayout33.setRowExpandRatio(1, 1.0F);
		this.gridLayout31.setWidth(100, Unit.PERCENTAGE);
		this.gridLayout31.setHeight(58, Unit.PIXELS);
		this.horizontalLayout11.addComponent(this.gridLayout31);
		this.horizontalLayout11.setComponentAlignment(this.gridLayout31, Alignment.MIDDLE_CENTER);
		this.horizontalLayout11.setExpandRatio(this.gridLayout31, 4.0F);
		this.gridLayout32.setWidth(-1, Unit.PIXELS);
		this.gridLayout32.setHeight(58, Unit.PIXELS);
		this.horizontalLayout11.addComponent(this.gridLayout32);
		this.horizontalLayout11.setExpandRatio(this.gridLayout32, 2.0F);
		this.gridLayout33.setWidth(100, Unit.PERCENTAGE);
		this.gridLayout33.setHeight(58, Unit.PIXELS);
		this.horizontalLayout11.addComponent(this.gridLayout33);
		this.horizontalLayout11.setExpandRatio(this.gridLayout33, 2.0F);
		this.gridLayout12.setColumns(2);
		this.gridLayout12.setRows(3);
		this.label34.setSizeUndefined();
		this.gridLayout12.addComponent(this.label34, 0, 0);
		this.label59.setSizeUndefined();
		this.gridLayout12.addComponent(this.label59, 0, 1);
		final CustomComponent gridLayout12_hSpacer = new CustomComponent();
		gridLayout12_hSpacer.setSizeFull();
		this.gridLayout12.addComponent(gridLayout12_hSpacer, 1, 0, 1, 1);
		this.gridLayout12.setColumnExpandRatio(1, 1.0F);
		final CustomComponent gridLayout12_vSpacer = new CustomComponent();
		gridLayout12_vSpacer.setSizeFull();
		this.gridLayout12.addComponent(gridLayout12_vSpacer, 0, 2, 0, 2);
		this.gridLayout12.setRowExpandRatio(2, 1.0F);
		this.gridLayout21.setColumns(2);
		this.gridLayout21.setRows(2);
		this.cmbTuan.setWidth(230, Unit.PIXELS);
		this.cmbTuan.setHeight(-1, Unit.PIXELS);
		this.gridLayout21.addComponent(this.cmbTuan, 0, 0);
		this.gridLayout21.setComponentAlignment(this.cmbTuan, Alignment.TOP_CENTER);
		final CustomComponent gridLayout21_hSpacer = new CustomComponent();
		gridLayout21_hSpacer.setSizeFull();
		this.gridLayout21.addComponent(gridLayout21_hSpacer, 1, 0, 1, 0);
		this.gridLayout21.setColumnExpandRatio(1, 1.0F);
		final CustomComponent gridLayout21_vSpacer = new CustomComponent();
		gridLayout21_vSpacer.setSizeFull();
		this.gridLayout21.addComponent(gridLayout21_vSpacer, 0, 1, 0, 1);
		this.gridLayout21.setRowExpandRatio(1, 1.0F);
		this.gridLayout13.setColumns(1);
		this.gridLayout13.setRows(2);
		this.btnWeeklyReport.setWidth(160, Unit.PIXELS);
		this.btnWeeklyReport.setHeight(-1, Unit.PIXELS);
		this.gridLayout13.addComponent(this.btnWeeklyReport, 0, 0);
		this.gridLayout13.setComponentAlignment(this.btnWeeklyReport, Alignment.TOP_RIGHT);
		this.gridLayout13.setColumnExpandRatio(0, 10.0F);
		final CustomComponent gridLayout13_vSpacer = new CustomComponent();
		gridLayout13_vSpacer.setSizeFull();
		this.gridLayout13.addComponent(gridLayout13_vSpacer, 0, 1, 0, 1);
		this.gridLayout13.setRowExpandRatio(1, 1.0F);
		this.gridLayout12.setWidth(100, Unit.PERCENTAGE);
		this.gridLayout12.setHeight(58, Unit.PIXELS);
		this.horizontalLayout10.addComponent(this.gridLayout12);
		this.horizontalLayout10.setComponentAlignment(this.gridLayout12, Alignment.MIDDLE_CENTER);
		this.horizontalLayout10.setExpandRatio(this.gridLayout12, 4.0F);
		this.gridLayout21.setWidth(-1, Unit.PIXELS);
		this.gridLayout21.setHeight(58, Unit.PIXELS);
		this.horizontalLayout10.addComponent(this.gridLayout21);
		this.horizontalLayout10.setExpandRatio(this.gridLayout21, 2.0F);
		this.gridLayout13.setWidth(100, Unit.PERCENTAGE);
		this.gridLayout13.setHeight(58, Unit.PIXELS);
		this.horizontalLayout10.addComponent(this.gridLayout13);
		this.horizontalLayout10.setComponentAlignment(this.gridLayout13, Alignment.MIDDLE_CENTER);
		this.horizontalLayout10.setExpandRatio(this.gridLayout13, 2.0F);
		this.gridLayout14.setColumns(2);
		this.gridLayout14.setRows(3);
		this.label55.setSizeUndefined();
		this.gridLayout14.addComponent(this.label55, 0, 0);
		this.label60.setSizeUndefined();
		this.gridLayout14.addComponent(this.label60, 0, 1);
		final CustomComponent gridLayout14_hSpacer = new CustomComponent();
		gridLayout14_hSpacer.setSizeFull();
		this.gridLayout14.addComponent(gridLayout14_hSpacer, 1, 0, 1, 1);
		this.gridLayout14.setColumnExpandRatio(1, 1.0F);
		final CustomComponent gridLayout14_vSpacer = new CustomComponent();
		gridLayout14_vSpacer.setSizeFull();
		this.gridLayout14.addComponent(gridLayout14_vSpacer, 0, 2, 0, 2);
		this.gridLayout14.setRowExpandRatio(2, 1.0F);
		this.gridLayout22.setColumns(3);
		this.gridLayout22.setRows(2);
		this.cmbThang.setWidth(100, Unit.PIXELS);
		this.cmbThang.setHeight(-1, Unit.PIXELS);
		this.gridLayout22.addComponent(this.cmbThang, 0, 0);
		this.comboBox.setWidth(120, Unit.PIXELS);
		this.comboBox.setHeight(-1, Unit.PIXELS);
		this.gridLayout22.addComponent(this.comboBox, 1, 0);
		final CustomComponent gridLayout22_hSpacer = new CustomComponent();
		gridLayout22_hSpacer.setSizeFull();
		this.gridLayout22.addComponent(gridLayout22_hSpacer, 2, 0, 2, 0);
		this.gridLayout22.setColumnExpandRatio(2, 1.0F);
		final CustomComponent gridLayout22_vSpacer = new CustomComponent();
		gridLayout22_vSpacer.setSizeFull();
		this.gridLayout22.addComponent(gridLayout22_vSpacer, 0, 1, 1, 1);
		this.gridLayout22.setRowExpandRatio(1, 1.0F);
		this.gridLayout15.setColumns(1);
		this.gridLayout15.setRows(2);
		this.btnMonthlyReport.setWidth(160, Unit.PIXELS);
		this.btnMonthlyReport.setHeight(-1, Unit.PIXELS);
		this.gridLayout15.addComponent(this.btnMonthlyReport, 0, 0);
		this.gridLayout15.setComponentAlignment(this.btnMonthlyReport, Alignment.TOP_RIGHT);
		this.gridLayout15.setColumnExpandRatio(0, 10.0F);
		final CustomComponent gridLayout15_vSpacer = new CustomComponent();
		gridLayout15_vSpacer.setSizeFull();
		this.gridLayout15.addComponent(gridLayout15_vSpacer, 0, 1, 0, 1);
		this.gridLayout15.setRowExpandRatio(1, 1.0F);
		this.gridLayout14.setWidth(100, Unit.PERCENTAGE);
		this.gridLayout14.setHeight(58, Unit.PERCENTAGE);
		this.horizontalLayout7.addComponent(this.gridLayout14);
		this.horizontalLayout7.setComponentAlignment(this.gridLayout14, Alignment.MIDDLE_CENTER);
		this.horizontalLayout7.setExpandRatio(this.gridLayout14, 4.0F);
		this.gridLayout22.setWidth(-1, Unit.PIXELS);
		this.gridLayout22.setHeight(58, Unit.PIXELS);
		this.horizontalLayout7.addComponent(this.gridLayout22);
		this.horizontalLayout7.setExpandRatio(this.gridLayout22, 2.0F);
		this.gridLayout15.setWidth(100, Unit.PERCENTAGE);
		this.gridLayout15.setHeight(88, Unit.PIXELS);
		this.horizontalLayout7.addComponent(this.gridLayout15);
		this.horizontalLayout7.setComponentAlignment(this.gridLayout15, Alignment.MIDDLE_CENTER);
		this.horizontalLayout7.setExpandRatio(this.gridLayout15, 2.0F);
		this.gridLayout16.setColumns(2);
		this.gridLayout16.setRows(3);
		this.label54.setSizeUndefined();
		this.gridLayout16.addComponent(this.label54, 0, 0);
		this.label61.setSizeUndefined();
		this.gridLayout16.addComponent(this.label61, 0, 1);
		final CustomComponent gridLayout16_hSpacer = new CustomComponent();
		gridLayout16_hSpacer.setSizeFull();
		this.gridLayout16.addComponent(gridLayout16_hSpacer, 1, 0, 1, 1);
		this.gridLayout16.setColumnExpandRatio(1, 1.0F);
		final CustomComponent gridLayout16_vSpacer = new CustomComponent();
		gridLayout16_vSpacer.setSizeFull();
		this.gridLayout16.addComponent(gridLayout16_vSpacer, 0, 2, 0, 2);
		this.gridLayout16.setRowExpandRatio(2, 1.0F);
		this.gridLayout23.setColumns(3);
		this.gridLayout23.setRows(2);
		this.cmbQuy.setWidth(100, Unit.PIXELS);
		this.cmbQuy.setHeight(-1, Unit.PIXELS);
		this.gridLayout23.addComponent(this.cmbQuy, 0, 0);
		this.cmbTenQuy.setWidth(120, Unit.PIXELS);
		this.cmbTenQuy.setHeight(-1, Unit.PIXELS);
		this.gridLayout23.addComponent(this.cmbTenQuy, 1, 0);
		final CustomComponent gridLayout23_hSpacer = new CustomComponent();
		gridLayout23_hSpacer.setSizeFull();
		this.gridLayout23.addComponent(gridLayout23_hSpacer, 2, 0, 2, 0);
		this.gridLayout23.setColumnExpandRatio(2, 1.0F);
		final CustomComponent gridLayout23_vSpacer = new CustomComponent();
		gridLayout23_vSpacer.setSizeFull();
		this.gridLayout23.addComponent(gridLayout23_vSpacer, 0, 1, 1, 1);
		this.gridLayout23.setRowExpandRatio(1, 1.0F);
		this.gridLayout17.setColumns(1);
		this.gridLayout17.setRows(2);
		this.btnQuarterReport.setWidth(160, Unit.PIXELS);
		this.btnQuarterReport.setHeight(-1, Unit.PIXELS);
		this.gridLayout17.addComponent(this.btnQuarterReport, 0, 0);
		this.gridLayout17.setComponentAlignment(this.btnQuarterReport, Alignment.TOP_RIGHT);
		this.gridLayout17.setColumnExpandRatio(0, 10.0F);
		final CustomComponent gridLayout17_vSpacer = new CustomComponent();
		gridLayout17_vSpacer.setSizeFull();
		this.gridLayout17.addComponent(gridLayout17_vSpacer, 0, 1, 0, 1);
		this.gridLayout17.setRowExpandRatio(1, 1.0F);
		this.gridLayout16.setSizeFull();
		this.horizontalLayout8.addComponent(this.gridLayout16);
		this.horizontalLayout8.setComponentAlignment(this.gridLayout16, Alignment.MIDDLE_CENTER);
		this.horizontalLayout8.setExpandRatio(this.gridLayout16, 4.0F);
		this.gridLayout23.setSizeUndefined();
		this.horizontalLayout8.addComponent(this.gridLayout23);
		this.horizontalLayout8.setExpandRatio(this.gridLayout23, 2.0F);
		this.gridLayout17.setWidth(100, Unit.PERCENTAGE);
		this.gridLayout17.setHeight(89, Unit.PIXELS);
		this.horizontalLayout8.addComponent(this.gridLayout17);
		this.horizontalLayout8.setComponentAlignment(this.gridLayout17, Alignment.MIDDLE_CENTER);
		this.horizontalLayout8.setExpandRatio(this.gridLayout17, 2.0F);
		this.gridLayout25.setColumns(2);
		this.gridLayout25.setRows(3);
		this.label57.setSizeUndefined();
		this.gridLayout25.addComponent(this.label57, 0, 0);
		this.label62.setSizeUndefined();
		this.gridLayout25.addComponent(this.label62, 0, 1);
		final CustomComponent gridLayout25_hSpacer = new CustomComponent();
		gridLayout25_hSpacer.setSizeFull();
		this.gridLayout25.addComponent(gridLayout25_hSpacer, 1, 0, 1, 1);
		this.gridLayout25.setColumnExpandRatio(1, 1.0F);
		final CustomComponent gridLayout25_vSpacer = new CustomComponent();
		gridLayout25_vSpacer.setSizeFull();
		this.gridLayout25.addComponent(gridLayout25_vSpacer, 0, 2, 0, 2);
		this.gridLayout25.setRowExpandRatio(2, 1.0F);
		this.gridLayout24.setColumns(2);
		this.gridLayout24.setRows(2);
		this.cmbQuy2.setWidth(100, Unit.PIXELS);
		this.cmbQuy2.setHeight(-1, Unit.PIXELS);
		this.gridLayout24.addComponent(this.cmbQuy2, 0, 0);
		final CustomComponent gridLayout24_hSpacer = new CustomComponent();
		gridLayout24_hSpacer.setSizeFull();
		this.gridLayout24.addComponent(gridLayout24_hSpacer, 1, 0, 1, 0);
		this.gridLayout24.setColumnExpandRatio(1, 1.0F);
		final CustomComponent gridLayout24_vSpacer = new CustomComponent();
		gridLayout24_vSpacer.setSizeFull();
		this.gridLayout24.addComponent(gridLayout24_vSpacer, 0, 1, 0, 1);
		this.gridLayout24.setRowExpandRatio(1, 1.0F);
		this.gridLayout26.setColumns(1);
		this.gridLayout26.setRows(2);
		this.btnYearlyReport.setWidth(160, Unit.PIXELS);
		this.btnYearlyReport.setHeight(-1, Unit.PIXELS);
		this.gridLayout26.addComponent(this.btnYearlyReport, 0, 0);
		this.gridLayout26.setComponentAlignment(this.btnYearlyReport, Alignment.TOP_RIGHT);
		this.gridLayout26.setColumnExpandRatio(0, 10.0F);
		final CustomComponent gridLayout26_vSpacer = new CustomComponent();
		gridLayout26_vSpacer.setSizeFull();
		this.gridLayout26.addComponent(gridLayout26_vSpacer, 0, 1, 0, 1);
		this.gridLayout26.setRowExpandRatio(1, 1.0F);
		this.gridLayout25.setSizeFull();
		this.horizontalLayout9.addComponent(this.gridLayout25);
		this.horizontalLayout9.setComponentAlignment(this.gridLayout25, Alignment.MIDDLE_CENTER);
		this.horizontalLayout9.setExpandRatio(this.gridLayout25, 4.0F);
		this.gridLayout24.setSizeUndefined();
		this.horizontalLayout9.addComponent(this.gridLayout24);
		this.horizontalLayout9.setExpandRatio(this.gridLayout24, 2.0F);
		this.gridLayout26.setWidth(100, Unit.PERCENTAGE);
		this.gridLayout26.setHeight(69, Unit.PIXELS);
		this.horizontalLayout9.addComponent(this.gridLayout26);
		this.horizontalLayout9.setComponentAlignment(this.gridLayout26, Alignment.MIDDLE_CENTER);
		this.horizontalLayout9.setExpandRatio(this.gridLayout26, 2.0F);
		this.gridLayout11.setColumns(1);
		this.gridLayout11.setRows(6);
		this.horizontalLayout11.setSizeFull();
		this.gridLayout11.addComponent(this.horizontalLayout11, 0, 0);
		this.horizontalLayout10.setWidth(100, Unit.PERCENTAGE);
		this.horizontalLayout10.setHeight(30, Unit.PERCENTAGE);
		this.gridLayout11.addComponent(this.horizontalLayout10, 0, 1);
		this.horizontalLayout7.setSizeFull();
		this.gridLayout11.addComponent(this.horizontalLayout7, 0, 2);
		this.horizontalLayout8.setSizeFull();
		this.gridLayout11.addComponent(this.horizontalLayout8, 0, 3);
		this.horizontalLayout9.setSizeFull();
		this.gridLayout11.addComponent(this.horizontalLayout9, 0, 4);
		this.gridLayout11.setColumnExpandRatio(0, 10.0F);
		final CustomComponent gridLayout11_vSpacer = new CustomComponent();
		gridLayout11_vSpacer.setSizeFull();
		this.gridLayout11.addComponent(gridLayout11_vSpacer, 0, 5, 0, 5);
		this.gridLayout11.setRowExpandRatio(5, 1.0F);
		this.gridLayout10.setColumns(1);
		this.gridLayout10.setRows(1);
		this.gridLayout11.setSizeFull();
		this.gridLayout10.addComponent(this.gridLayout11, 0, 0);
		this.gridLayout10.setColumnExpandRatio(0, 10.0F);
		this.gridLayout10.setRowExpandRatio(0, 10.0F);
		this.gridLayout2.setColumns(1);
		this.gridLayout2.setRows(2);
		this.browserFrame.setSizeFull();
		this.gridLayout2.addComponent(this.browserFrame, 0, 0);
		this.gridLayout2.setColumnExpandRatio(0, 10.0F);
		final CustomComponent gridLayout2_vSpacer = new CustomComponent();
		gridLayout2_vSpacer.setSizeFull();
		this.gridLayout2.addComponent(gridLayout2_vSpacer, 0, 1, 0, 1);
		this.gridLayout2.setRowExpandRatio(1, 1.0F);
		this.gridLayout8.setColumns(3);
		this.gridLayout8.setRows(2);
		this.gridLayout2.setWidth(1, Unit.PIXELS);
		this.gridLayout2.setHeight(1, Unit.PIXELS);
		this.gridLayout8.addComponent(this.gridLayout2, 0, 0);
		this.btnbaocaoTongHop.setWidth(120, Unit.PIXELS);
		this.btnbaocaoTongHop.setHeight(-1, Unit.PIXELS);
		this.gridLayout8.addComponent(this.btnbaocaoTongHop, 1, 0);
		this.gridLayout8.setComponentAlignment(this.btnbaocaoTongHop, Alignment.TOP_RIGHT);
		this.btnTheoTieuChi.setWidth(150, Unit.PIXELS);
		this.btnTheoTieuChi.setHeight(-1, Unit.PIXELS);
		this.gridLayout8.addComponent(this.btnTheoTieuChi, 2, 0);
		this.gridLayout8.setColumnExpandRatio(1, 10.0F);
		final CustomComponent gridLayout8_vSpacer = new CustomComponent();
		gridLayout8_vSpacer.setSizeFull();
		this.gridLayout8.addComponent(gridLayout8_vSpacer, 0, 1, 2, 1);
		this.gridLayout8.setRowExpandRatio(1, 1.0F);
		this.gridLayout6.setColumns(1);
		this.gridLayout6.setRows(2);
		this.FromDate.setWidth(100, Unit.PERCENTAGE);
		this.FromDate.setHeight(-1, Unit.PIXELS);
		this.gridLayout6.addComponent(this.FromDate, 0, 0);
		this.gridLayout6.setColumnExpandRatio(0, 10.0F);
		final CustomComponent gridLayout6_vSpacer = new CustomComponent();
		gridLayout6_vSpacer.setSizeFull();
		this.gridLayout6.addComponent(gridLayout6_vSpacer, 0, 1, 0, 1);
		this.gridLayout6.setRowExpandRatio(1, 1.0F);
		this.txtTuoiTu.setWidth(100, Unit.PERCENTAGE);
		this.txtTuoiTu.setHeight(-1, Unit.PIXELS);
		this.horizontalLayout5.addComponent(this.txtTuoiTu);
		this.horizontalLayout5.setComponentAlignment(this.txtTuoiTu, Alignment.MIDDLE_CENTER);
		this.horizontalLayout5.setExpandRatio(this.txtTuoiTu, 10.0F);
		this.label53.setSizeUndefined();
		this.horizontalLayout5.addComponent(this.label53);
		this.horizontalLayout5.setComponentAlignment(this.label53, Alignment.MIDDLE_CENTER);
		this.txtTuoiDen.setWidth(100, Unit.PERCENTAGE);
		this.txtTuoiDen.setHeight(-1, Unit.PIXELS);
		this.horizontalLayout5.addComponent(this.txtTuoiDen);
		this.horizontalLayout5.setComponentAlignment(this.txtTuoiDen, Alignment.MIDDLE_CENTER);
		this.horizontalLayout5.setExpandRatio(this.txtTuoiDen, 10.0F);
		this.txtNamSinhTu.setWidth(100, Unit.PERCENTAGE);
		this.txtNamSinhTu.setHeight(-1, Unit.PIXELS);
		this.horizontalLayout6.addComponent(this.txtNamSinhTu);
		this.horizontalLayout6.setExpandRatio(this.txtNamSinhTu, 10.0F);
		this.label32.setSizeUndefined();
		this.horizontalLayout6.addComponent(this.label32);
		this.txtNamSinhDen.setWidth(100, Unit.PERCENTAGE);
		this.txtNamSinhDen.setHeight(-1, Unit.PIXELS);
		this.horizontalLayout6.addComponent(this.txtNamSinhDen);
		this.horizontalLayout6.setExpandRatio(this.txtNamSinhDen, 10.0F);
		this.dateNgayTamGiuTu.setWidth(100, Unit.PERCENTAGE);
		this.dateNgayTamGiuTu.setHeight(-1, Unit.PIXELS);
		this.horizontalLayout3.addComponent(this.dateNgayTamGiuTu);
		this.horizontalLayout3.setComponentAlignment(this.dateNgayTamGiuTu, Alignment.MIDDLE_CENTER);
		this.horizontalLayout3.setExpandRatio(this.dateNgayTamGiuTu, 10.0F);
		this.label42.setSizeUndefined();
		this.horizontalLayout3.addComponent(this.label42);
		this.horizontalLayout3.setComponentAlignment(this.label42, Alignment.MIDDLE_CENTER);
		this.dateNgayTamGiuDen.setWidth(100, Unit.PERCENTAGE);
		this.dateNgayTamGiuDen.setHeight(-1, Unit.PIXELS);
		this.horizontalLayout3.addComponent(this.dateNgayTamGiuDen);
		this.horizontalLayout3.setComponentAlignment(this.dateNgayTamGiuDen, Alignment.MIDDLE_CENTER);
		this.horizontalLayout3.setExpandRatio(this.dateNgayTamGiuDen, 10.0F);
		this.gridLayout4.setColumns(2);
		this.gridLayout4.setRows(23);
		this.label2.setSizeUndefined();
		this.gridLayout4.addComponent(this.label2, 0, 0);
		this.label3.setSizeUndefined();
		this.gridLayout4.addComponent(this.label3, 0, 1);
		this.gridLayout6.setWidth(100, Unit.PERCENTAGE);
		this.gridLayout6.setHeight(-1, Unit.PIXELS);
		this.gridLayout4.addComponent(this.gridLayout6, 1, 1);
		this.label56.setSizeUndefined();
		this.gridLayout4.addComponent(this.label56, 0, 2);
		this.ToDate.setWidth(100, Unit.PERCENTAGE);
		this.ToDate.setHeight(-1, Unit.PIXELS);
		this.gridLayout4.addComponent(this.ToDate, 1, 2);
		this.gridLayout4.setComponentAlignment(this.ToDate, Alignment.TOP_RIGHT);
		this.label6.setSizeUndefined();
		this.gridLayout4.addComponent(this.label6, 0, 3);
		this.cmbLinhVuc.setWidth(100, Unit.PERCENTAGE);
		this.cmbLinhVuc.setHeight(-1, Unit.PIXELS);
		this.gridLayout4.addComponent(this.cmbLinhVuc, 1, 3);
		this.label31.setSizeUndefined();
		this.gridLayout4.addComponent(this.label31, 0, 4);
		this.label37.setSizeUndefined();
		this.gridLayout4.addComponent(this.label37, 0, 5);
		this.cmbToChuc.setWidth(100, Unit.PERCENTAGE);
		this.cmbToChuc.setHeight(-1, Unit.PIXELS);
		this.gridLayout4.addComponent(this.cmbToChuc, 1, 5);
		this.label10.setSizeUndefined();
		this.gridLayout4.addComponent(this.label10, 0, 6);
		this.txtTenNvp.setWidth(100, Unit.PERCENTAGE);
		this.txtTenNvp.setHeight(-1, Unit.PIXELS);
		this.gridLayout4.addComponent(this.txtTenNvp, 1, 6);
		this.label11.setSizeUndefined();
		this.gridLayout4.addComponent(this.label11, 0, 7);
		this.txtDiaChiNvp.setWidth(100, Unit.PERCENTAGE);
		this.txtDiaChiNvp.setHeight(-1, Unit.PIXELS);
		this.gridLayout4.addComponent(this.txtDiaChiNvp, 1, 7);
		this.label36.setSizeUndefined();
		this.gridLayout4.addComponent(this.label36, 0, 8);
		this.cmbNgheNghiep.setWidth(100, Unit.PERCENTAGE);
		this.cmbNgheNghiep.setHeight(-1, Unit.PIXELS);
		this.gridLayout4.addComponent(this.cmbNgheNghiep, 1, 8);
		this.label52.setSizeUndefined();
		this.gridLayout4.addComponent(this.label52, 0, 9);
		this.horizontalLayout5.setSizeFull();
		this.gridLayout4.addComponent(this.horizontalLayout5, 1, 9);
		this.label33.setSizeUndefined();
		this.gridLayout4.addComponent(this.label33, 0, 10);
		this.horizontalLayout6.setSizeFull();
		this.gridLayout4.addComponent(this.horizontalLayout6, 1, 10);
		this.label28.setSizeUndefined();
		this.gridLayout4.addComponent(this.label28, 0, 11);
		this.cmbLoaiGiayTo.setWidth(100, Unit.PERCENTAGE);
		this.cmbLoaiGiayTo.setHeight(-1, Unit.PIXELS);
		this.gridLayout4.addComponent(this.cmbLoaiGiayTo, 1, 11);
		this.label.setSizeUndefined();
		this.gridLayout4.addComponent(this.label, 0, 12);
		this.cmbNoiCapTv.setWidth(100, Unit.PERCENTAGE);
		this.cmbNoiCapTv.setHeight(-1, Unit.PIXELS);
		this.gridLayout4.addComponent(this.cmbNoiCapTv, 1, 12);
		this.label23.setSizeUndefined();
		this.gridLayout4.addComponent(this.label23, 0, 13);
		this.txtSoGiayTo.setWidth(100, Unit.PERCENTAGE);
		this.txtSoGiayTo.setHeight(-1, Unit.PIXELS);
		this.gridLayout4.addComponent(this.txtSoGiayTo, 1, 13);
		this.label38.setSizeUndefined();
		this.gridLayout4.addComponent(this.label38, 0, 14);
		this.cmbHangGplx.setWidth(100, Unit.PERCENTAGE);
		this.cmbHangGplx.setHeight(-1, Unit.PIXELS);
		this.gridLayout4.addComponent(this.cmbHangGplx, 1, 14);
		this.label24.setSizeUndefined();
		this.gridLayout4.addComponent(this.label24, 0, 15);
		this.label25.setSizeUndefined();
		this.gridLayout4.addComponent(this.label25, 0, 16);
		this.cmbLoaiPt.setWidth(100, Unit.PERCENTAGE);
		this.cmbLoaiPt.setHeight(-1, Unit.PIXELS);
		this.gridLayout4.addComponent(this.cmbLoaiPt, 1, 16);
		this.label39.setSizeUndefined();
		this.gridLayout4.addComponent(this.label39, 0, 17);
		this.txtBKS.setWidth(100, Unit.PERCENTAGE);
		this.txtBKS.setHeight(-1, Unit.PIXELS);
		this.gridLayout4.addComponent(this.txtBKS, 1, 17);
		this.label40.setSizeUndefined();
		this.gridLayout4.addComponent(this.label40, 0, 18);
		this.label41.setSizeUndefined();
		this.gridLayout4.addComponent(this.label41, 0, 19);
		this.horizontalLayout3.setWidth(100, Unit.PERCENTAGE);
		this.horizontalLayout3.setHeight(-1, Unit.PIXELS);
		this.gridLayout4.addComponent(this.horizontalLayout3, 1, 19);
		this.label12.setSizeUndefined();
		this.gridLayout4.addComponent(this.label12, 0, 20);
		this.cmbCapPheDuyet.setWidth(100, Unit.PERCENTAGE);
		this.cmbCapPheDuyet.setHeight(-1, Unit.PIXELS);
		this.gridLayout4.addComponent(this.cmbCapPheDuyet, 1, 20);
		this.label50.setSizeUndefined();
		this.gridLayout4.addComponent(this.label50, 0, 21);
		this.cmbTrangThaiXl.setWidth(100, Unit.PERCENTAGE);
		this.cmbTrangThaiXl.setHeight(-1, Unit.PIXELS);
		this.gridLayout4.addComponent(this.cmbTrangThaiXl, 1, 21);
		this.gridLayout4.setColumnExpandRatio(1, 10.0F);
		final CustomComponent gridLayout4_vSpacer = new CustomComponent();
		gridLayout4_vSpacer.setSizeFull();
		this.gridLayout4.addComponent(gridLayout4_vSpacer, 0, 22, 1, 22);
		this.gridLayout4.setRowExpandRatio(22, 1.0F);
		this.txtPhatTienTu.setWidth(100, Unit.PERCENTAGE);
		this.txtPhatTienTu.setHeight(-1, Unit.PIXELS);
		this.horizontalLayout4.addComponent(this.txtPhatTienTu);
		this.horizontalLayout4.setExpandRatio(this.txtPhatTienTu, 10.0F);
		this.label21.setSizeUndefined();
		this.horizontalLayout4.addComponent(this.label21);
		this.txtPhatTienDen.setWidth(100, Unit.PERCENTAGE);
		this.txtPhatTienDen.setHeight(-1, Unit.PIXELS);
		this.horizontalLayout4.addComponent(this.txtPhatTienDen);
		this.horizontalLayout4.setExpandRatio(this.txtPhatTienDen, 10.0F);
		this.pdFTuocTuNgay.setWidth(100, Unit.PERCENTAGE);
		this.pdFTuocTuNgay.setHeight(-1, Unit.PIXELS);
		this.horizontalLayout.addComponent(this.pdFTuocTuNgay);
		this.horizontalLayout.setExpandRatio(this.pdFTuocTuNgay, 10.0F);
		this.label16.setSizeUndefined();
		this.horizontalLayout.addComponent(this.label16);
		this.pdFTuocDenNgay.setWidth(100, Unit.PERCENTAGE);
		this.pdFTuocDenNgay.setHeight(-1, Unit.PIXELS);
		this.horizontalLayout.addComponent(this.pdFTuocDenNgay);
		this.horizontalLayout.setExpandRatio(this.pdFTuocDenNgay, 10.0F);
		this.gridLayout5.setColumns(2);
		this.gridLayout5.setRows(24);
		this.label43.setSizeUndefined();
		this.gridLayout5.addComponent(this.label43, 0, 0);
		this.label44.setSizeUndefined();
		this.gridLayout5.addComponent(this.label44, 0, 1);
		this.cmbTinh.setWidth(100, Unit.PERCENTAGE);
		this.cmbTinh.setHeight(-1, Unit.PIXELS);
		this.gridLayout5.addComponent(this.cmbTinh, 1, 1);
		this.label45.setSizeUndefined();
		this.gridLayout5.addComponent(this.label45, 0, 2);
		this.cmbQuanHuyen.setWidth(100, Unit.PERCENTAGE);
		this.cmbQuanHuyen.setHeight(-1, Unit.PIXELS);
		this.gridLayout5.addComponent(this.cmbQuanHuyen, 1, 2);
		this.label46.setSizeUndefined();
		this.gridLayout5.addComponent(this.label46, 0, 3);
		this.cmbPhuongXa.setWidth(100, Unit.PERCENTAGE);
		this.cmbPhuongXa.setHeight(-1, Unit.PIXELS);
		this.gridLayout5.addComponent(this.cmbPhuongXa, 1, 3);
		this.label47.setSizeUndefined();
		this.gridLayout5.addComponent(this.label47, 0, 4);
		this.cmbQuocLo.setWidth(100, Unit.PERCENTAGE);
		this.cmbQuocLo.setHeight(-1, Unit.PIXELS);
		this.gridLayout5.addComponent(this.cmbQuocLo, 1, 4);
		this.label48.setSizeUndefined();
		this.gridLayout5.addComponent(this.label48, 0, 5);
		this.cmbTuyenDuong.setWidth(100, Unit.PERCENTAGE);
		this.cmbTuyenDuong.setHeight(-1, Unit.PIXELS);
		this.gridLayout5.addComponent(this.cmbTuyenDuong, 1, 5);
		this.label13.setSizeUndefined();
		this.gridLayout5.addComponent(this.label13, 0, 6);
		this.label14.setSizeUndefined();
		this.gridLayout5.addComponent(this.label14, 0, 7);
		this.cmbHinhThucPhat.setWidth(100, Unit.PERCENTAGE);
		this.cmbHinhThucPhat.setHeight(-1, Unit.PIXELS);
		this.gridLayout5.addComponent(this.cmbHinhThucPhat, 1, 7);
		this.label22.setSizeUndefined();
		this.initUI2();
	}
	
	private void initUI2() {
		this.gridLayout5.addComponent(this.label22, 0, 8);
		this.horizontalLayout4.setSizeFull();
		this.gridLayout5.addComponent(this.horizontalLayout4, 1, 8);
		this.label20.setSizeUndefined();
		this.gridLayout5.addComponent(this.label20, 0, 9);
		this.cmbHinhThucNP.setWidth(100, Unit.PERCENTAGE);
		this.cmbHinhThucNP.setHeight(-1, Unit.PIXELS);
		this.gridLayout5.addComponent(this.cmbHinhThucNP, 1, 9);
		this.label19.setSizeUndefined();
		this.gridLayout5.addComponent(this.label19, 0, 10);
		this.cmbNopTrucTuyenQua.setWidth(100, Unit.PERCENTAGE);
		this.cmbNopTrucTuyenQua.setHeight(-1, Unit.PIXELS);
		this.gridLayout5.addComponent(this.cmbNopTrucTuyenQua, 1, 10);
		this.label18.setSizeUndefined();
		this.gridLayout5.addComponent(this.label18, 0, 11);
		this.label17.setSizeUndefined();
		this.gridLayout5.addComponent(this.label17, 0, 12);
		this.cmbXpbs.setWidth(100, Unit.PERCENTAGE);
		this.cmbXpbs.setHeight(-1, Unit.PIXELS);
		this.gridLayout5.addComponent(this.cmbXpbs, 1, 12);
		this.label15.setSizeUndefined();
		this.gridLayout5.addComponent(this.label15, 0, 13);
		this.horizontalLayout.setWidth(100, Unit.PERCENTAGE);
		this.horizontalLayout.setHeight(-1, Unit.PIXELS);
		this.gridLayout5.addComponent(this.horizontalLayout, 1, 13);
		this.label27.setSizeUndefined();
		this.gridLayout5.addComponent(this.label27, 0, 14);
		this.label8.setSizeUndefined();
		this.gridLayout5.addComponent(this.label8, 0, 15);
		this.cmbNhomHv.setWidth(100, Unit.PERCENTAGE);
		this.cmbNhomHv.setHeight(-1, Unit.PIXELS);
		this.gridLayout5.addComponent(this.cmbNhomHv, 1, 15);
		this.label9.setSizeUndefined();
		this.gridLayout5.addComponent(this.label9, 0, 16);
		this.cmbnhomHvvp.setWidth(100, Unit.PERCENTAGE);
		this.cmbnhomHvvp.setHeight(-1, Unit.PIXELS);
		this.gridLayout5.addComponent(this.cmbnhomHvvp, 1, 16);
		this.label7.setSizeUndefined();
		this.gridLayout5.addComponent(this.label7, 0, 17);
		this.cmbNghiDinh.setWidth(100, Unit.PERCENTAGE);
		this.cmbNghiDinh.setHeight(-1, Unit.PIXELS);
		this.gridLayout5.addComponent(this.cmbNghiDinh, 1, 17);
		this.label26.setSizeUndefined();
		this.gridLayout5.addComponent(this.label26, 0, 18);
		this.cmbHvvp.setWidth(100, Unit.PERCENTAGE);
		this.cmbHvvp.setHeight(-1, Unit.PIXELS);
		this.gridLayout5.addComponent(this.cmbHvvp, 1, 18);
		this.label30.setSizeUndefined();
		this.gridLayout5.addComponent(this.label30, 0, 19);
		this.label49.setSizeUndefined();
		this.gridLayout5.addComponent(this.label49, 0, 20);
		this.cmbLoaiBb.setWidth(100, Unit.PERCENTAGE);
		this.cmbLoaiBb.setHeight(-1, Unit.PIXELS);
		this.gridLayout5.addComponent(this.cmbLoaiBb, 1, 20);
		this.label29.setSizeUndefined();
		this.gridLayout5.addComponent(this.label29, 0, 21);
		this.txtSoBb.setWidth(100, Unit.PERCENTAGE);
		this.txtSoBb.setHeight(-1, Unit.PIXELS);
		this.gridLayout5.addComponent(this.txtSoBb, 1, 21);
		this.label51.setSizeUndefined();
		this.gridLayout5.addComponent(this.label51, 0, 22);
		this.cmbCanBoLap.setWidth(100, Unit.PERCENTAGE);
		this.cmbCanBoLap.setHeight(-1, Unit.PIXELS);
		this.gridLayout5.addComponent(this.cmbCanBoLap, 1, 22);
		this.gridLayout5.setColumnExpandRatio(1, 10.0F);
		final CustomComponent gridLayout5_vSpacer = new CustomComponent();
		gridLayout5_vSpacer.setSizeFull();
		this.gridLayout5.addComponent(gridLayout5_vSpacer, 0, 23, 1, 23);
		this.gridLayout5.setRowExpandRatio(23, 1.0F);
		this.gridLayout18.setColumns(2);
		this.gridLayout18.setRows(2);
		this.gridLayout4.setWidth(100, Unit.PERCENTAGE);
		this.gridLayout4.setHeight(-1, Unit.PIXELS);
		this.gridLayout18.addComponent(this.gridLayout4, 0, 0);
		this.gridLayout5.setWidth(100, Unit.PERCENTAGE);
		this.gridLayout5.setHeight(-1, Unit.PIXELS);
		this.gridLayout18.addComponent(this.gridLayout5, 1, 0);
		this.gridLayout18.setColumnExpandRatio(0, 10.0F);
		this.gridLayout18.setColumnExpandRatio(1, 10.0F);
		final CustomComponent gridLayout18_vSpacer = new CustomComponent();
		gridLayout18_vSpacer.setSizeFull();
		this.gridLayout18.addComponent(gridLayout18_vSpacer, 0, 1, 1, 1);
		this.gridLayout18.setRowExpandRatio(1, 1.0F);
		this.gridLayout9.setColumns(1);
		this.gridLayout9.setRows(2);
		this.gridLayout8.setWidth(100, Unit.PERCENTAGE);
		this.gridLayout8.setHeight(-1, Unit.PIXELS);
		this.gridLayout9.addComponent(this.gridLayout8, 0, 0);
		this.gridLayout18.setSizeFull();
		this.gridLayout9.addComponent(this.gridLayout18, 0, 1);
		this.gridLayout9.setColumnExpandRatio(0, 10.0F);
		this.gridLayout9.setRowExpandRatio(1, 10.0F);
		this.gridLayout19.setColumns(1);
		this.gridLayout19.setRows(2);
		this.button.setSizeUndefined();
		this.gridLayout19.addComponent(this.button, 0, 0);
		this.table.setSizeFull();
		this.gridLayout19.addComponent(this.table, 0, 1);
		this.gridLayout19.setColumnExpandRatio(0, 100.0F);
		this.gridLayout19.setRowExpandRatio(1, 100.0F);
		this.gridLayout10.setSizeFull();
		this.tabSheet.addTab(this.gridLayout10, "BÁO CÁO ĐỊNH KỲ", null);
		this.gridLayout9.setSizeFull();
		this.tabSheet.addTab(this.gridLayout9, "BÁO CÁO TỔNG HỢP", null);
		this.gridLayout19.setSizeFull();
		this.tabSheet.addTab(this.gridLayout19, "Tab", null);
		this.tabSheet.setSelectedTab(this.gridLayout10);
		this.gridLayout3.setColumns(2);
		this.gridLayout3.setRows(1);
		this.gridLayout7.setSizeFull();
		this.gridLayout3.addComponent(this.gridLayout7, 0, 0);
		this.tabSheet.setSizeFull();
		this.gridLayout3.addComponent(this.tabSheet, 1, 0);
		this.gridLayout3.setColumnExpandRatio(0, 31.0F);
		this.gridLayout3.setColumnExpandRatio(1, 75.0F);
		this.gridLayout3.setRowExpandRatio(0, 100.0F);
		this.gridLayout.setColumns(1);
		this.gridLayout.setRows(2);
		this.gridLayout3.setWidth(100, Unit.PERCENTAGE);
		this.gridLayout3.setHeight(-1, Unit.PIXELS);
		this.gridLayout.addComponent(this.gridLayout3, 0, 0);
		this.browserFrame2.setWidth(100, Unit.PERCENTAGE);
		this.browserFrame2.setHeight(-1, Unit.PIXELS);
		this.gridLayout.addComponent(this.browserFrame2, 0, 1);
		this.gridLayout.setColumnExpandRatio(0, 100.0F);
		this.gridLayout.setRowExpandRatio(0, 10.0F);
		this.gridLayout.setWidth(100, Unit.PERCENTAGE);
		this.gridLayout.setHeight(-1, Unit.PIXELS);
		this.setContent(this.gridLayout);
		this.setSizeFull();
	
		this.txtTimKiem.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.txtTimKiem_valueChange(event);
			}
		});
		this.checkBox.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.checkBox_valueChange(event);
			}
		});
		this.btnWeeklyReport3.addClickListener(event -> this.btnWeeklyReport3_buttonClick(event));
		this.btnWeeklyReport.addClickListener(event -> this.btnWeeklyReport_buttonClick(event));
		this.btnMonthlyReport.addClickListener(event -> this.btnMonthlyReport_buttonClick(event));
		this.btnQuarterReport.addClickListener(event -> this.btnQuarterReport_buttonClick(event));
		this.btnYearlyReport.addClickListener(event -> this.btnYearlyReport_buttonClick(event));
		this.btnbaocaoTongHop.addClickListener(event -> this.btnbaocaoTongHop_buttonClick(event));
		this.btnTheoTieuChi.addClickListener(event -> this.btnTheoTieuChi_buttonClick(event));
		this.FromDate.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.FromDate_valueChange(event);
			}
		});
		this.ToDate.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.ToDate_valueChange(event);
			}
		});
		this.cmbLinhVuc.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbLinhVuc_valueChange(event);
			}
		});
		this.cmbToChuc.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbToChuc_valueChange(event);
			}
		});
		this.txtTenNvp.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.txtTenNvp_valueChange(event);
			}
		});
		this.txtDiaChiNvp.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.txtDiaChiNvp_valueChange(event);
			}
		});
		this.cmbNgheNghiep.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbNgheNghiep_valueChange(event);
			}
		});
		this.txtTuoiTu.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.txtTuoiTu_valueChange(event);
			}
		});
		this.txtTuoiDen.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.txtTuoiDen_valueChange(event);
			}
		});
		this.txtNamSinhTu.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.txtNamSinhTu_valueChange(event);
			}
		});
		this.txtNamSinhDen.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.txtNamSinhDen_valueChange(event);
			}
		});
		this.cmbLoaiGiayTo.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbLoaiGiayTo_valueChange(event);
			}
		});
		this.cmbNoiCapTv.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbNoiCapTv_valueChange(event);
			}
		});
		this.txtSoGiayTo.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.txtSoGiayTo_valueChange(event);
			}
		});
		this.cmbHangGplx.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbHangGplx_valueChange(event);
			}
		});
		this.cmbLoaiPt.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbLoaiPt_valueChange(event);
			}
		});
		this.txtBKS.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.txtBKS_valueChange(event);
			}
		});
		this.dateNgayTamGiuTu.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.dateNgayTamGiuTu_valueChange(event);
			}
		});
		this.dateNgayTamGiuDen.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.dateNgayTamGiuDen_valueChange(event);
			}
		});
		this.cmbCapPheDuyet.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbCapPheDuyet_valueChange(event);
			}
		});
		this.cmbTrangThaiXl.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbTrangThaiXl_valueChange(event);
			}
		});
		this.cmbTinh.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbTinh_valueChange(event);
			}
		});
		this.cmbQuanHuyen.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbQuanHuyen_valueChange(event);
			}
		});
		this.cmbPhuongXa.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbPhuongXa_valueChange(event);
			}
		});
		this.cmbQuocLo.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbQuocLo_valueChange(event);
			}
		});
		this.cmbTuyenDuong.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbTuyenDuong_valueChange(event);
			}
		});
		this.cmbHinhThucPhat.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbHinhThucPhat_valueChange(event);
			}
		});
		this.txtPhatTienTu.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.txtPhatTienTu_valueChange(event);
			}
		});
		this.txtPhatTienDen.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.txtPhatTienDen_valueChange(event);
			}
		});
		this.cmbHinhThucNP.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbHinhThucNP_valueChange(event);
			}
		});
		this.cmbNopTrucTuyenQua.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbNopTrucTuyenQua_valueChange(event);
			}
		});
		this.cmbXpbs.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbXpbs_valueChange(event);
			}
		});
		this.pdFTuocTuNgay.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.pdFTuocTuNgay_valueChange(event);
			}
		});
		this.pdFTuocDenNgay.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.pdFTuocDenNgay_valueChange(event);
			}
		});
		this.cmbNhomHv.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbNhomHv_valueChange(event);
			}
		});
		this.cmbnhomHvvp.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbnhomHvvp_valueChange(event);
			}
		});
		this.cmbNghiDinh.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbNghiDinh_valueChange(event);
			}
		});
		this.cmbHvvp.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbHvvp_valueChange(event);
			}
		});
		this.cmbLoaiBb.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbLoaiBb_valueChange(event);
			}
		});
		this.txtSoBb.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.txtSoBb_valueChange(event);
			}
		});
		this.cmbCanBoLap.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(final Property.ValueChangeEvent event) {
				Bao_Cao_Tong_Hop.this.cmbCanBoLap_valueChange(event);
			}
		});
		this.button.addClickListener(event -> this.button_buttonClick(event));
	} // </generated-code>
	
	// <generated-code name="variables">
	private XdevButton btnWeeklyReport3, btnWeeklyReport, btnMonthlyReport, btnQuarterReport, btnYearlyReport,
			btnbaocaoTongHop, btnTheoTieuChi, button;
	private XdevComboBox<ChucVu> cmbCapPheDuyet;
	private XdevGridLayout gridLayout, gridLayout3, gridLayout7, gridLayout20, gridLayout10, gridLayout11, gridLayout31,
			gridLayout32, gridLayout33, gridLayout12, gridLayout21, gridLayout13, gridLayout14, gridLayout22,
			gridLayout15, gridLayout16, gridLayout23, gridLayout17, gridLayout25, gridLayout24, gridLayout26,
			gridLayout9, gridLayout8, gridLayout2, gridLayout18, gridLayout4, gridLayout6, gridLayout5, gridLayout19;
	private XdevHorizontalLayout horizontalLayout2, horizontalLayout11, horizontalLayout10, horizontalLayout7,
			horizontalLayout8, horizontalLayout9, horizontalLayout5, horizontalLayout6, horizontalLayout3,
			horizontalLayout4, horizontalLayout;
	private XdevComboBox<LoaiPhuongTien> cmbLoaiPt;
	private XdevPopupDateField pdfNgay, FromDate, ToDate, dateNgayTamGiuTu, dateNgayTamGiuDen, pdFTuocTuNgay,
			pdFTuocDenNgay;
	private XdevComboBox<HinhThucXuPhatBoSung> cmbXpbs;
	private XdevComboBox<?> cmbTuan, cmbThang, comboBox, cmbQuy, cmbTenQuy, cmbQuy2, cmbLinhVuc, cmbToChuc,
			cmbLoaiGiayTo, cmbTrangThaiXl, cmbHinhThucNP, cmbNhomHv, cmbLoaiBb;
	private XdevComboBox<NoicapGiayto> cmbNoiCapTv;
	private XdevTreeTable treeTable;
	private XdevTextField txtTimKiem, txtTenNvp, txtDiaChiNvp, txtTuoiTu, txtTuoiDen, txtNamSinhTu, txtNamSinhDen,
			txtSoGiayTo, txtBKS, txtPhatTienTu, txtPhatTienDen, txtSoBb;
	private XdevComboBox<AuthUser> cmbCanBoLap;
	private XdevLabel label5, label4, label58, label35, label34, label59, label55, label60, label54, label61, label57,
			label62, label2, label3, label56, label6, label31, label37, label10, label11, label36, label52, label53,
			label33, label32, label28, label, label23, label38, label24, label25, label39, label40, label41, label42,
			label12, label50, label43, label44, label45, label46, label47, label48, label13, label14, label22, label21,
			label20, label19, label18, label17, label15, label16, label27, label8, label9, label7, label26, label30,
			label49, label29, label51;
	private XdevComboBox<NghiDinhCp> cmbNghiDinh;
	private XdevBrowserFrame browserFrame, browserFrame2;
	private XdevTabSheet tabSheet;
	private XdevComboBox<DiaDanhHanhChinh> cmbTinh, cmbQuanHuyen, cmbPhuongXa;
	private XdevComboBox<NhomHvvp> cmbnhomHvvp;
	private XdevTable<BaoCaoTongHop> table;
	private XdevComboBox<HangGplx> cmbHangGplx;
	private XdevComboBox<KhoBacNganHang> cmbNopTrucTuyenQua;
	private XdevComboBox<NgheNghiep> cmbNgheNghiep;
	private XdevComboBox<QuocLoTuyenduong> cmbQuocLo, cmbTuyenDuong;
	private XdevCheckBox checkBox;
	private XdevComboBox<HinhThucXuPhatVphc> cmbHinhThucPhat;
	private XdevComboBox<HanhViViPham> cmbHvvp;
	// </generated-code>

}
