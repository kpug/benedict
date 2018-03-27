package cc.kpug.benedict.provider;

import lombok.Getter;
import lombok.Setter;

/**
 * User: before30 
 * Date: 2018. 3. 27.
 * Time: 21:05
 */
@Getter
@Setter
public class GitRepositoryInfo {

	private String fullName;

	private String defaultBranch;

	private String htmlUrl;

	public String getDownloadUrl() {
		return String.format("%s/archive/%s.zip", htmlUrl, defaultBranch);
	}
}
