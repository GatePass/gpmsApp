/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.reports;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.gpms.web.common.BondedItemManagement;
import org.gpms.web.domain.AssetsRepository;
import org.gpms.web.domain.UserAssetsRepository;
import org.gpms.web.domain.UsersRepository;
import org.gpms.web.entities.assets.UserAssetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author narenda.kumar
 * 
 */
@Service
public class ReportsBusinessSrv {

	private static final Logger logger = Logger
			.getLogger(ReportsBusinessSrv.class);

	@Autowired
	UserAssetsRepository userAssetsRepository;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	AssetsRepository assetsRepository;

	@Autowired
	BondedItemManagement bondedItemManagement;

	/**
	 * 
	 * @param usersPersonalEmail
	 * @return
	 */
	public List<ReportModel> getEmployeeGatePass(String usersPersonalEmail) {

		List<UserAssetEntity> userAssetEntityLst = userAssetsRepository
				.getUserAssetByCorpEmail(usersPersonalEmail);

		List<ReportModel> reportModelLst = new ArrayList<ReportModel>();

		Iterator<UserAssetEntity> userAssetEntityLstIter = userAssetEntityLst
				.iterator();

		while (userAssetEntityLstIter.hasNext()) {
			UserAssetEntity singleUserAssetEntity = userAssetEntityLstIter
					.next();
			ReportModel reportModel = new ReportModel();

			reportModel.setUserAssetId(singleUserAssetEntity.getUserAssetId());
			reportModel.setAssetId(singleUserAssetEntity.getAssetId());
			reportModel.setUserCorpEmail(singleUserAssetEntity
					.getUserCorpEmail());
			reportModel.setUserAssetIssueDate(singleUserAssetEntity
					.getUserAssetIssueDate());
			reportModel.setUserAssetReturnDate(singleUserAssetEntity
					.getUserAssetReturnDate());
			// if(singleUserAssetEntity.getUserAssetReturnProcessId() != null){
			//
			// bondedItemManagement.
			// reportModel.setAssetAssignComments(assetAssignComments);
			// }
			//

			reportModelLst.add(reportModel);

		}

		return reportModelLst;
	}

}
