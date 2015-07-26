/**
 * 
 */
package org.gpms.web.gpmsWeb.controller.assets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.gpms.web.gpmsBusinessSrv.assets.AssetMgmtBusinessSrv;
import org.gpms.web.gpmsBusinessSrv.assets.AssetModel;
import org.gpms.web.gpmsBusinessSrv.assets.AssetTypeModel;
import org.gpms.web.gpmsBusinessSrv.util.ApplicationConstants;
import org.gpms.web.gpmsBusinessSrv.util.DateUtil;
import org.gpms.web.gpmsWeb.common.GpmsValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author narenda.kumar
 * 
 */
@Controller
public class AssetController {

	private static final Logger logger = Logger
			.getLogger(AssetController.class);

	@Autowired
	AssetMgmtBusinessSrv assetMgmtBusinessSrv;

	/**
	 * 
	 * @param assetBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/newAsset", method = RequestMethod.GET)
	public ModelAndView newAsset(@ModelAttribute AssetBean assetBean,
			Model model) {

		List<AssetTypeModel> assetTypeModelLst = assetMgmtBusinessSrv
				.getAllAssetType();

		model.addAttribute("assetTypeModelLst", assetTypeModelLst);
		model.addAttribute("assetBean", assetBean);
		model.addAttribute("isPreDisabled", "true");
		return new ModelAndView("assets/newAsset");
	}

	/**
	 * 
	 * @param assetBean
	 * @param result
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/newAsset", method = RequestMethod.POST, params = "createAsset")
	public String createAsset(@ModelAttribute @Valid AssetBean assetBean,
			BindingResult result, Model model) throws IOException {

		logger.debug("createAsset");

		List<AssetTypeModel> assetTypeModelLst = assetMgmtBusinessSrv
				.getAllAssetType();
		model.addAttribute("assetTypeModelLst", assetTypeModelLst);
		model.addAttribute("assetBean", assetBean);
		model.addAttribute("isPreDisabled", "true");

		if (result.hasErrors()) {
			return "assets/newAsset";
		}

		AssetModel returnModel = null;

		if (assetBean != null) {
			AssetModel assetModel = copyBeanToModel(assetBean);

			if (assetModel != null) {
				assetModel
						.setAssetStatus(ApplicationConstants.ASSET_AVAILABLE_STATUS);
				returnModel = assetMgmtBusinessSrv.createAsset(assetModel);
			}
			assetBean.setAssetId(returnModel.getAssetId());
		}

		if (returnModel.getAssetId() != null) {
			model.addAttribute("isDisabled", "true");
		}

		model.addAttribute("assetBean", assetBean);

		return "assets/newAsset";
	}

	/**
	 * 
	 * @param assetBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/modifyDeleteAsset", method = RequestMethod.GET)
	public ModelAndView modifyDeleteAsset(@ModelAttribute AssetBean assetBean,
			Model model) {
		String flowType = (String) model.asMap().get("flowType");

		assetBean.setFlowType(flowType);
		model.addAttribute("flowType", flowType);
		model.addAttribute("isDisabled", "true");

		return new ModelAndView("assets/modifyDeleteAsset");
	}

	/**
	 * 
	 * @param assetBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/modifyDeleteAsset", method = RequestMethod.POST, params = "getAssetData")
	public ModelAndView getAssetData(@ModelAttribute AssetBean assetBean,
			BindingResult result, Model model) {

		FieldError assetIdFldErr = new FieldError("assetBean", "assetId",
				"Enter Asset Id to fetch data");
		String flowType = assetBean.getFlowType();

		if (assetBean != null && assetBean.getAssetId().equals("")) {
			result.addError(assetIdFldErr);
		}

		model.addAttribute("flowType", flowType);

		if (result.hasErrors()) {
			return new ModelAndView("assets/modifyDeleteAsset");
		}

		if (assetBean != null && assetBean.getAssetId() != null) {
			AssetModel assetModel = assetMgmtBusinessSrv.getAssetById(assetBean
					.getAssetId());

			if (logger.isDebugEnabled()) {
				logger.debug("assetModel " + assetModel);
			}

			if (assetModel != null) {
				assetBean = copyModelToBean(assetModel);
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("assetBean " + assetBean);
		}

		model.addAttribute("isDisabled", "false");
		model.addAttribute("assetBean", assetBean);
		return new ModelAndView("assets/modifyDeleteAsset");
	}

	/**
	 * 
	 * @param assetBean
	 * @param result
	 * @param model
	 * @param redirectAttrs
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/modifyDeleteAsset", method = RequestMethod.POST, params = "modifyAsset")
	public String modifyAsset(@ModelAttribute @Valid AssetBean assetBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs)
			throws IOException {

		List<AssetTypeModel> assetTypeModelLst = assetMgmtBusinessSrv
				.getAllAssetType();

		result = GpmsValidators.validateDateField(
				assetBean.getAssetPurchaseDate(), "assetBean",
				"assetPurchaseDate", result);

		// if (assetBean != null
		// & (ApplicationConstants.ASSET_ASSIGNED_STATUS.equals(assetBean
		// .getAssetStatus()) || ApplicationConstants.ASSET_IN_PROCESS_STATUS
		// .equals(assetBean.getAssetStatus()))) {
		// FieldError dateFldErr = new FieldError(
		// "assetBean",
		// "assetStatus",
		// "The asset is in status (assigned or In-Process) & its status cannot be changed");
		// result.addError(dateFldErr);
		// }

		if (result.hasErrors()) {
			return "assets/modifyDeleteAsset";
		}

		AssetModel returnModel = null;

		if (assetBean != null) {
			AssetModel assetModel = copyBeanToModel(assetBean);

			if (logger.isDebugEnabled()) {
				logger.debug("assetModel " + assetModel);
			}

			if (assetModel != null) {
				returnModel = assetMgmtBusinessSrv.modifyAsset(assetModel);
			}
			assetBean = copyModelToBean(returnModel);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("assetBean " + assetBean);
		}

		if (returnModel != null && returnModel.getAssetId() != null) {
			model.addAttribute("isDisabled", "true");
		}

		model.addAttribute("assetTypeModelLst", assetTypeModelLst);
		model.addAttribute("assetBean", assetBean);

		model.addAttribute("flowType", "modifyAsset");
		redirectAttrs.addFlashAttribute("flowType", "modifyAsset");
		return "assets/modifyDeleteAsset";
	}

	/**
	 * 
	 * @param assetBean
	 * @param result
	 * @param model
	 * @param redirectAttrs
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/modifyDeleteAsset", method = RequestMethod.POST, params = "deleteAsset")
	public String deleteAsset(@ModelAttribute @Valid AssetBean assetBean,
			BindingResult result, Model model, RedirectAttributes redirectAttrs)
			throws IOException {

		// if (assetBean != null
		// & (!ApplicationConstants.ASSET_REMOVED_STATUS.equals(assetBean
		// .getAssetStatus()))) {
		// FieldError dateFldErr = new FieldError("assetBean", "assetStatus",
		// "The asset is in status (Removed) & its status cannot be deleted");
		// result.addError(dateFldErr);
		// }

		// if (result.hasErrors()) {
		// return "assets/modifyDeleteAsset";
		// }

		if (assetBean != null && assetBean.getAssetId() != null) {

			if (logger.isDebugEnabled()) {
				logger.debug("assetBean " + assetBean);
			}

			String assetId = assetBean.getAssetId();
			assetMgmtBusinessSrv.deleteAssetById(assetId);
		}

		model.addAttribute("flowType", "deleteAsset");
		model.addAttribute("isDisabled", "true");
		redirectAttrs.addFlashAttribute("flowType", "deleteAsset");
		return "assets/modifyDeleteAsset";
	}

	/**
	 * 
	 * @param assetBean
	 * @return
	 */
	private AssetModel copyBeanToModel(AssetBean assetBean) {

		AssetModel assetModel = new AssetModel();
		assetModel.setAssetId(assetBean.getAssetId());
		assetModel.setAssetBarCode(assetBean.getAssetBarCode());
		assetModel.setAssetTypeId(assetBean.getAssetTypeId());
		if (assetBean.getAssetPurchaseDate() != null) {
			Date purchaseDate = DateUtil.getSQLDateForTimeStamp(assetBean
					.getAssetPurchaseDate());
			assetModel.setAssetPurchaseDate(purchaseDate);
		}
		if (assetBean.getAssetRemovalDate() != null) {
			Date removalDate = DateUtil.getSQLDateForTimeStamp(assetBean
					.getAssetRemovalDate());
			assetModel.setAssetRemovalDate(removalDate);
		}
		assetModel.setAssetStatus(assetBean.getAssetStatus());
		return assetModel;
	}

	/**
	 * 
	 * @param assetModel
	 * @return
	 */
	private AssetBean copyModelToBean(AssetModel assetModel) {

		AssetBean assetBean = new AssetBean();
		assetBean.setAssetId(assetModel.getAssetId());
		assetBean.setAssetBarCode(assetModel.getAssetBarCode());
		assetBean.setAssetTypeId(assetModel.getAssetTypeId());

		if (assetModel.getAssetPurchaseDate() != null) {
			assetBean.setAssetPurchaseDate(DateUtil
					.getDateFormattedString(assetModel.getAssetPurchaseDate()));
		}

		if (assetModel.getAssetRemovalDate() != null) {
			assetBean.setAssetRemovalDate(DateUtil
					.getDateFormattedString(assetModel.getAssetRemovalDate()));
		}
		assetBean.setAssetStatus(assetModel.getAssetStatus());
		return assetBean;
	}

	// ********************************AssetType*************************************************
	/**
	 * 
	 * @param assetTypeBean
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/newAssetType", method = RequestMethod.GET)
	public ModelAndView newAssetType(
			@ModelAttribute AssetTypeBean assetTypeBean, BindingResult result,
			Model model) {

		model.addAttribute("assetTypeBean", assetTypeBean);

		return new ModelAndView("assets/newAssetType");
	}

	/**
	 * 
	 * @param assetTypeBean
	 * @param result
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/newAssetType", method = RequestMethod.POST, params = "createAssetType")
	public String createAssetType(
			@ModelAttribute @Valid AssetTypeBean assetTypeBean,
			BindingResult result, Model model) throws IOException {

		AssetTypeModel assetTypeModel = new AssetTypeModel();
		assetTypeModel.setAssetTypeName(assetTypeBean.getAssetTypeName());
		assetTypeModel.setAssetTypeDesc(assetTypeBean.getAssetTypeDesc());
		AssetTypeModel returnModel = assetMgmtBusinessSrv
				.createAssetType(assetTypeModel);
		assetTypeBean.setAssetTypeId(returnModel.getAssetTypeId());

		if (returnModel.getAssetTypeId() != null) {
			model.addAttribute("isDisabled", "true");
		}

		model.addAttribute("assetTypeBean", assetTypeBean);

		return "assets/newAssetType";
	}

}
