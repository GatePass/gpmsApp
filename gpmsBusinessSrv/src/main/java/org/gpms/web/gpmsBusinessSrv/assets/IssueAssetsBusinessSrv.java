/**
 * 
 */
package org.gpms.web.gpmsBusinessSrv.assets;

import java.util.Date;

import org.gpms.web.bondedItemIssue.BondedItemIssue;
import org.gpms.web.domain.AssetsRepository;
import org.gpms.web.domain.UserAssetsRepository;
import org.gpms.web.domain.UsersRepository;
import org.gpms.web.entities.assets.AssetsEntity;
import org.gpms.web.entities.assets.UserAssetEntity;
import org.gpms.web.entities.users.UsersLoginEntity;
import org.gpms.web.gpmsBusinessSrv.util.ApplicationConstants;
import org.gpms.web.gpmsBusinessSrv.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author narenda.kumar
 * 
 */
@Service
public class IssueAssetsBusinessSrv {

	@Autowired
	BondedItemIssue bondedItemIssue;

	@Autowired
	UserAssetsRepository userAssetsRepository;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	AssetsRepository assetsRepository;

	public void IssueBondedItems(AssetAssignModel assetAssignModel) {

		UserAssetEntity userAssetEntity = new UserAssetEntity();

		UsersLoginEntity userLoginEntity = usersRepository
				.getUserByCorpEmailId(assetAssignModel.getUserCorpEmail());
		if (userLoginEntity == null) {
			return;
		}

		AssetsEntity assetsEntity = assetsRepository
				.getAssetById(assetAssignModel.getAssetId());
		if (assetsEntity == null) {
			return;
		}

		userAssetEntity.setAssetId(assetAssignModel.getAssetId());
		userAssetEntity.setUserCorpEmail(assetAssignModel.getUserCorpEmail());
		userAssetEntity.setUserAssetIssueDate(DateUtil
				.getSQLDate(assetAssignModel.getUserAssetIssueDate()));
		userAssetEntity.setUserAssetIssueProcessId("-999999999");
		userAssetEntity.setCreateDate(new Date());

		String userAssetId = userAssetsRepository
				.addAssetToUser(userAssetEntity);
		userAssetEntity.setUserAssetId(userAssetId);

		String processInstanceId = bondedItemIssue
				.startBondItemIssueProcess(userAssetId);
		userAssetEntity.setUserAssetIssueProcessId(processInstanceId);

		userAssetId = userAssetsRepository
				.updateAssetInfoOfUser(userAssetEntity);

		assetsEntity.setAssetStatus(ApplicationConstants.ASSET_ASSIGNED_STATUS);
		assetsRepository.updateAssetInfo(assetsEntity);

		bondedItemIssue.assignProcessToUserAndGroup(processInstanceId,
				userLoginEntity.getUserCorpEmail(),
				userLoginEntity.getUserGroupId());

	}

}
