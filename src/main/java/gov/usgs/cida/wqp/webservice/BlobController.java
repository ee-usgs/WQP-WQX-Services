package gov.usgs.cida.wqp.webservice;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import gov.usgs.cida.wqp.dao.BlobDao;
import gov.usgs.cida.wqp.parameter.FilterParameters;
import gov.usgs.cida.wqp.util.HttpConstants;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class BlobController {

	protected final BlobDao blobDao;

	@Autowired
	public BlobController(BlobDao blobDao) {
		this.blobDao = blobDao;
	}

	@ApiOperation(value="Return zipped artifact with all files available for the specified organization/project.")
	@GetMapping(value="/organizations/{organization}/projects/{projectIdentifier}/files")
	public void projectBlobFilesGetRestRequest(HttpServletRequest request, HttpServletResponse response, @ApiIgnore FilterParameters filter,
			@PathVariable("organization") String organization, @PathVariable("projectIdentifier") String projectIdentifier) throws IOException {
		response.setHeader(HttpConstants.HEADER_CONTENT_DISPOSITION,"attachment; filename=project.zip");
		OutputStream out = response.getOutputStream();
		ZipOutputStream target = new ZipOutputStream(out);
		blobDao.getFiles(target, organization, projectIdentifier);
		target.finish();
		target.close();
		out.flush();
	}

}