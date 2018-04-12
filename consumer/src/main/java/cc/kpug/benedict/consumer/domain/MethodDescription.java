package cc.kpug.benedict.consumer.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by jayden.uk on 2018. 4. 7...
 */
@Getter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "method_description")
public class MethodDescription {
    private @Id @GeneratedValue Long id;

    @Column
    final private String projectName;
    @Column
    final private String branchName;
    @Column
    final private String filePath;
    @Column
    final private Integer lineNumber;
    @Column
    final private String name;
}