package part2.common.pojo;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 9:02
 *@Description:version1
 *@version:1.0
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    //共有的传输对象
    private Integer id;
    private String userName;
    private Boolean sex;
}
