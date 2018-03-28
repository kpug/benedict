package cc.kpug.benedict.provider.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * User: before30 
 * Date: 2018. 3. 27.
 * Time: 21:05
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitRepositoryInfo {

	@JsonProperty("full_name")
	private String fullName;

	@JsonProperty("default_branch")
	private String defaultBranch;

	@JsonProperty("html_url")
	private String htmlUrl;

	public String getDownloadUrl() {
		return String.format("%s/archive/%s.zip", htmlUrl, defaultBranch);
	}
}
