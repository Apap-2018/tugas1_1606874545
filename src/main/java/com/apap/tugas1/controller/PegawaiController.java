package com.apap.tugas1.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.InstansiDB;
import com.apap.tugas1.repository.JabatanDB;
import com.apap.tugas1.repository.ProvinsiDB;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;




@Controller
public class PegawaiController {
	
@Autowired
private PegawaiService pegawaiService;

@Autowired
private JabatanService jabatanService;

@Autowired
private JabatanDB jabatanDB;

@Autowired
private InstansiDB instansiDB;

@Autowired
private ProvinsiDB provinsiDB;
	
	@RequestMapping("/")
	private String home(Model model) {
		
		model.addAttribute("jabatan", jabatanDB.findAll());
		return "home";
	}
	
	@RequestMapping(value= {"/pegawai"}, method=RequestMethod.GET)
	private String viewPegawai(String nip, Model model)	{
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		model.addAttribute("pegawai",pegawai);
		return "view-pegawai";
	}
	
	
	@RequestMapping("/pegawai/tambah")
	private String tambahPegawai(Model model) {
		
		model.addAttribute("provinsi", provinsiDB.findAll());
		model.addAttribute("instansi", instansiDB.findAll());
		model.addAttribute("jabatan", jabatanDB.findAll());
	
		return "tambahPegawai";
	}
	
	@RequestMapping(value="/pegawai/tambah",method=RequestMethod.POST)
	private String hapusJabatan(@ModelAttribute PegawaiModel pegawaiBaru, Model model) {
		
		
		model.addAttribute("notif", "Jabatan");
		model.addAttribute("keterangan", "hapus");
		
		return "berhasil";
		
	}
	

	

	
	
	
	

}
