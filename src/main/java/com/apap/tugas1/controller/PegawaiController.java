package com.apap.tugas1.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.InstansiDB;
import com.apap.tugas1.repository.JabatanDB;
import com.apap.tugas1.repository.ProvinsiDB;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;




@Controller
public class PegawaiController {
	
@Autowired
private PegawaiService pegawaiService;

@Autowired
private JabatanService jabatanService;

@Autowired
private ProvinsiService provinsiService;

@Autowired
private InstansiService instansiService;

@Autowired
private JabatanDB jabatanDB;

@Autowired
private InstansiDB instansiDB;

@Autowired
private ProvinsiDB provinsiDB;


	
	@RequestMapping("/")
	private String home(Model model) {
		
		model.addAttribute("jabatan", jabatanDB.findAll());
		model.addAttribute("instansi", instansiDB.findAll());
		return "home";
	}
	
	@RequestMapping(value= {"/pegawai"}, method=RequestMethod.GET)
	private String viewPegawai(String nip, Model model)	{
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		
		Double gaji = pegawaiService.getGajiPegawai(nip);
		
		model.addAttribute("pegawai",pegawai);
		model.addAttribute("gaji", gaji);
		return "view-pegawai";
	}
	
	
	@RequestMapping("/pegawai/tambah")
	private String tambahPegawai(Model model) {
		
		PegawaiModel peg = new PegawaiModel();
		if (peg.getJabatanList()==null) {
			peg.setJabatanList(new ArrayList());
		}
		peg.getJabatanList().add(new JabatanModel());
		List<ProvinsiModel> provinsiList = provinsiDB.findAll();
		List<JabatanModel> jabatanList = jabatanDB.findAll();
		model.addAttribute("jabatanList",jabatanList);
		model.addAttribute("pegawai", peg);
		model.addAttribute("provinsi", provinsiList);
		return "tambahPegawai";
		
	}
	

	
	
	@RequestMapping(value = "/pegawai/tambah/instansi",method = RequestMethod.GET)
	private @ResponseBody List<InstansiModel> cekInstansi(@RequestParam(value="provinsiId") long provinsiId) {
		System.out.println(provinsiId+"AAAA");
		ProvinsiModel provinsi = provinsiService.getProvinsiById(provinsiId).get();
		
		return provinsi.getInstansiList();
	}
	
	@RequestMapping(value="/pegawai/tambah",method = RequestMethod.POST, params= {"addRow"})
	private String addRow (@ModelAttribute PegawaiModel pegawai, Model model, BindingResult bindingResult) {
		if (pegawai.getJabatanList() == null) {
			pegawai.setJabatanList(new ArrayList());
		}
		System.out.println(pegawai.getJabatanList().size());
		pegawai.getJabatanList().add(new JabatanModel());
		
		List<JabatanModel> jabatanList = jabatanDB.findAll();
		List<ProvinsiModel> provinsiList = provinsiDB.findAll();
		model.addAttribute("provinsi", provinsiList);
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("jabatanList",jabatanList);
		return "tambahPegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST, params= {"submit"})
	private String tambahPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
		
		String nipGenerate = pegawaiService.buatNip(pegawai);
		
		pegawai.setNip(nipGenerate);
		
		pegawaiService.tambahPegawai(pegawai);
		String msg = "Pegawai dengan NIP "+nipGenerate+" berhasil ditambahkan";
		
		return "redirect:/";
	}
	
	
	
	@RequestMapping(value= {"/pegawai/ubah"}, method=RequestMethod.GET)
	private String ubahPegawai(String nip, Model model)	{
		
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		System.out.println(pegawai.getNama()+" hehehe");
		
		
		List<JabatanModel> jabatanList = jabatanDB.findAll();
		List<ProvinsiModel> provinsiList = provinsiDB.findAll();
		model.addAttribute("provinsi", provinsiList);
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("jabatanList",jabatanList);
		
		model.addAttribute("instansi", pegawai.getInstansi());
	
		
		return "ubahPegawai";
	}
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST, params= {"submit"})
	private String ubahPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
		System.out.println(pegawai.getJabatanList());
		
		pegawaiService.ubahPegawai(pegawai);
		return "redirect:/";
	}
	
	@RequestMapping(value="/pegawai/ubah",method = RequestMethod.POST, params= {"addRow"})
	private String addRowUpdate (@ModelAttribute PegawaiModel pegawai, Model model, BindingResult bindingResult) {
		if (pegawai.getJabatanList() == null) {
			pegawai.setJabatanList(new ArrayList());
		}
		System.out.println(pegawai.getJabatanList().size());
		pegawai.getJabatanList().add(new JabatanModel());
		
		List<JabatanModel> jabatanList = jabatanDB.findAll();
		List<ProvinsiModel> provinsiList = provinsiDB.findAll();
		model.addAttribute("provinsi", provinsiList);
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("jabatanList",jabatanList);
		return "ubahPegawai";
	}

	
/*	@RequestMapping(value= {"/pegawai/cari"}, method=RequestMethod.GET)
	private String cari(String nip, Model model)	{
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		
		Double gaji = pegawaiService.getGajiPegawai(nip);
		
		model.addAttribute("pegawai",pegawai);
		model.addAttribute("gaji", gaji);
		return "view-pegawai";
	}*/
	/*
	@RequestMapping(value= "/pegawai/cari", method = RequestMethod.GET)
	private String search(@RequestParam (value = "idProvinsi", required = false) String idProvinsi, 
			@RequestParam (value = "idInstansi", required = false) String idInstansi,
			@RequestParam (value = "idJabatan", required = false) String idJabatan, Model model) {
		
		//filter by provinsi, instansi, jabatan 
		if (idProvinsi != null && idInstansi != null && idJabatan != null) {
			
			
			InstansiModel instansi = instansiService.getInstansiDetailById(Long.parseLong(idInstansi));
		
			
			JabatanModel jabatan = jabatanService.getJabatanDetailById(Long.parseLong(idJabatan));
			List<PegawaiModel> listPegawai = pegawaiService.findByInstansiAndJabatanPegawai(instansi, jabatan);
			
			model.addAttribute("listPegawai", listPegawai);

			
			List<ProvinsiModel> listProvinsi = provinsiDB.findAll();
			model.addAttribute("listProvinsi", listProvinsi);

			
			List<InstansiModel> listInstansi = instansiDB.findAll();
			model.addAttribute("listInstansi", listInstansi);

			
			List<JabatanModel> listJabatan = jabatanDB.findAll();
			model.addAttribute("listJabatan", listJabatan);

			model.addAttribute("title", "Cari Pegawai"); 

		
	}
	
		return "search";	
	
	
	
	
	}*/
}
