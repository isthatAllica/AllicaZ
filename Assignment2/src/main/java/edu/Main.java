package edu;

import edu.whu.framework.BootstrapException;
import edu.whu.framework.BootstrapService;

// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {
    public static void main(String[] args) {
        try {
            BootstrapService.start("/myapp.properties");
        } catch (BootstrapException e) {
            System.out.println(e.getErrorType()+":"+e.getMessage());
        }
    }
}