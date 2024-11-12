package in.ashokit.service;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.ashokit.binding.CoResponse;
import in.ashokit.entity.CitizenAppEntity;
import in.ashokit.entity.CoTriggersEntity;
import in.ashokit.entity.DcCaseEntity;
import in.ashokit.entity.EligDtlsEntity;
import in.ashokit.repo.CitizenAppRepo;
import in.ashokit.repo.CoTriggersRepo;
import in.ashokit.repo.DcCaseRepo;
import in.ashokit.repo.EligDtlsRepo;

@Service
public class CoServiceImpl implements CoService {

	@Autowired
	private CoTriggersRepo coTriggersRepo;

	@Autowired
	private EligDtlsRepo eligDtlsRepo;

	@Autowired
	private CitizenAppRepo citizenAppRepo;

	@Autowired
	private DcCaseRepo dcCaseRepo;

	@Override
	public CoResponse processPendingTriggers() {

		CitizenAppEntity citizenAppEntity = null;

		// fetch all pending triggers
		List<CoTriggersEntity> entities = coTriggersRepo.findByTrgStatus("Pending");

		// process each pending triggers
		for (CoTriggersEntity entity : entities) {

			// get eligibility data based on caseNumer
			EligDtlsEntity eligEntity = eligDtlsRepo.findByCaseNum(entity.getCaseNum());

			// get citizen data based on the caseNumber
			Optional<DcCaseEntity> byId = dcCaseRepo.findById(entity.getCaseNum());
			if (byId.isPresent()) {
				DcCaseEntity dcCaseEntity = byId.get();
				Integer appId = dcCaseEntity.getAppId();
				Optional<CitizenAppEntity> byId2 = citizenAppRepo.findById(appId);
				if (byId2.isPresent()) {
					citizenAppEntity = byId2.get();
				}

			}
			// generate pdf with elig details
			generatePdf(eligEntity,citizenAppEntity);
			
			// send pdf to citizen mail
			// store the pdf and update the trigger as complete

		}

		// return summary
		return null;
	}

	
	private void generatePdf(EligDtlsEntity elg, CitizenAppEntity entity) {

		FileOutputStream fos= null;
		try {
			fos = new FileOutputStream(new File(elg.getCaseNum() + ".pdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, fos);
		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("EligibilityReport", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		// Create a table with 2 columns
		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 1.5f, 3.0f, 1.5f, 3.0f});
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.blue);
		cell.setPadding(5);

		font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.white);

		cell.setPhrase(new Phrase(" Citizen Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Case Number", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Name", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Plan Start Date", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Plan End Date", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Benefit Amount", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Denial Reason" , font));
        table.addCell(cell);
        
        table.addCell(elg.getHolderName());
        table.addCell(elg.getCaseNum()+"");
        table.addCell(elg.getPlanName());
        table.addCell(elg.getPlanStartDate() +"");
        table.addCell(elg.getPlanEndDate() +"");
        table.addCell(elg.getBenefiteAmt() +"");
        table.addCell(elg.getDenialReasion());

	}

}
