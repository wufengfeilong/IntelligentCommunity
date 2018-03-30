package com.fujisoft.ic.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xrichtext.ImageLoader;
import cn.droidlover.xrichtext.XRichText;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.fujisoft.ic.R;

import java.io.IOException;
import java.util.List;

public class NoticeDetailActivity extends AppCompatActivity {

    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.notice_detail_title)
    TextView noticeDetailTitle;
    @BindView(R.id.notice_detail_date)
    TextView noticeDetailDate;
    @BindView(R.id.richText)
    XRichText richText;
    String TEXT = "<h1 class=\"csdn_top\">Windows下从零开始搭建JAVA Web，Android开发环境</h1>\n" +
            "        <div class=\"article_bar clearfix\">\n" +
            "            <div class=\"artical_tag\">\n" +
            "                <span class=\"original\">\n" +
            "                原创                </span>\n" +
            "                <span class=\"time\">2016年10月25日 11:48:37</span>\n" +
            "            </div>\n" +
            "\n" +
            "            <ul class=\"article_tags clearfix csdn-tracking-statistics tracking-click\" data-mod=\"popu_377\" >\n" +
            "                <li class=\"tit\">标签：</li>\n" +
            "\n" +
            "<!--          [startarticletags]-->\n" +
            "                <!--          [endarticletags]-->\n" +
            "            </ul>\n" +
            "            <ul class=\"right_bar\">\n" +
            "                <li><button class=\"btn-noborder\"><i class=\"icon iconfont icon-read\"></i><span class=\"txt\">249</span></button></li>\n" +
            "                <li class=\"edit\">\n" +
            "                    <a class=\"btn-noborder\" href=\"\" >\n" +
            "                        <i class=\"icon iconfont icon-bianji\"></i><span class=\"txt\">编辑</span>\n" +
            "                    </a>\n" +
            "                </li>\n" +
            "                <li class=\"del\">\n" +
            "                    <a class=\"btn-noborder\" onclick=\"javascript:deleteArticle(fileName);return false;\">\n" +
            "                        <i class=\"icon iconfont icon-shanchu\"></i><span class=\"txt\">删除</span>\n" +
            "                    </a>\n" +
            "                </li>\n" +
            "            </ul>\n" +
            "        </div>\n" +
            "        <div id=\"article_content\" class=\"article_content csdn-tracking-statistics tracking-click\" data-mod=\"popu_519\" data-dsm=\"post\">\n" +
            "                            <div class=\"markdown_views\">\n" +
            "                        <p><strong>本篇教你电脑Windows系统下从零开始搭建可以开发Java Web，Android的开发环境。</strong></p>\n" +
            "\n" +
            "<h2 id=\"一安装jdk\"><strong>一、安装JDK</strong></h2>\n" +
            "\n" +
            "<p>JDK是 Java 语言的软件开发工具包。JDK是整个java开发的核心，它包含了JAVA的运行环境，JAVA工具和JAVA基础的类库。</p>\n" +
            "\n" +
            "<p><strong>JDK官网下载：</strong> <br>\n" +
            "<a href=\"http://www.oracle.com/technetwork/java/javase/downloads/index.html\">http://www.oracle.com/technetwork/java/javase/downloads/index.html</a> <br>\n" +
            "（*选中Accept License Agreement才可以下载）</p>\n" +
            "\n" +
            "<p><img src=\"https://img-blog.csdn.net/20161025104802787\" alt=\"JDK下载\" title=\"\"></p>\n" +
            "\n" +
            "<p><strong>JDK的配置：</strong> <br>\n" +
            "1、安装包下载完成后，双击安装，使用默认路径，即C:\\Program Files\\Java。 <br>\n" +
            "2、安装完成后，配置环境变量，在系统环境变量里添加一个变量名为JAVA_HOME环境变量。 <br>\n" +
            "3、在变量值处填写上JDK安装目录的bin文件夹的路径，即C:\\Program Files\\Java\\jdk1.8.0。 <br>\n" +
            "4、系统变量里添加名为Path的变量，值填写为%JAVA_HOME%\\bin;。至此，JDK的配置就算完成了。</p>\n" +
            "\n" +
            "<h2 id=\"二安装eclipse\"><strong>二、安装Eclipse</strong></h2>\n" +
            "\n" +
            "<p>Eclipse是用来开发Java Web项目的开发工具。</p>\n" +
            "\n" +
            "<p><strong>Eclipse JavaEE官网下载：</strong> <br>\n" +
            "<a href=\"https://www.eclipse.org/downloads/eclipse-packages/\">https://www.eclipse.org/downloads/eclipse-packages/</a> <br>\n" +
            "（*建议下载Eclipse IDE for Java EE Developers版本）</p>\n" +
            "\n" +
            "\n" +
            "\n" +
            "<h2 id=\"三安装tomcat\"><strong>三、安装Tomcat</strong></h2>\n" +
            "\n" +
            "<p>Tomcat是用来开发和调试Java Web项目的轻量级应用服务器。</p>\n" +
            "\n" +
            "<p><strong>Tomcat官网下载：</strong> <br>\n" +
            "<a href=\"http://tomcat.apache.org/\">http://tomcat.apache.org/</a> <br>\n" +
            "（*下载如下图的安装版本即可）</p>\n" +
            "\n" +
            "<p><img src=\"https://img-blog.csdn.net/20161025103937447\" alt=\"下载Eclipse版本\" title=\"\"></p>\n" +
            "\n" +
            "\n" +
            "\n" +
            "<h2 id=\"四安装mysql\"><strong>四、安装MySQL</strong></h2>\n" +
            "\n" +
            "<p>MySQL是一个关系型数据库管理系统。</p>\n" +
            "\n" +
            "<p><strong>MySQL官网：</strong> <br>\n" +
            "<a href=\"http://dev.mysql.com/downloads/mysql/\">http://dev.mysql.com/downloads/mysql/</a> <br>\n" +
            "（*开发Java Web时，还需要在官网下载连接Eclipse的jar包）</p>\n" +
            "\n" +
            "<h2 id=\"五安装android-studio\"><strong>五、安装Android Studio</strong></h2>\n" +
            "\n" +
            "<p>Android Studio开发移动设备（手机等）安卓程序的工具。</p>\n" +
            "\n" +
            "<p><strong>Android Studio中文社区（官网）：</strong> <br>\n" +
            "<a href=\"http://www.android-studio.org/\">http://www.android-studio.org/</a> <br>\n" +
            "（*直接下载带Android SDK的安装版本）</p>                </div>\n" +
            "                                                <link rel=\"stylesheet\" href=\"https://csdnimg.cn/release/phoenix/production/markdown_views-68a8aad09e.css\" />\n" +
            "                                    </div>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        noticeDetailTitle.setText(getIntent().getStringExtra("title"));
        noticeDetailDate.setText(getIntent().getStringExtra("date"));

        richText
                .callback(new XRichText.BaseClickCallback() {

                    @Override
                    public boolean onLinkClick(String url) {
                        Toast.makeText(NoticeDetailActivity.this, "" + url, Toast.LENGTH_SHORT).show();
                        return true;
                    }

                    @Override
                    public void onImageClick(List<String> urlList, int position) {
                        super.onImageClick(urlList, position);
                        Toast.makeText(NoticeDetailActivity.this, "图片:" + position, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFix(XRichText.ImageHolder holder) {
                        super.onFix(holder);
                        if (holder.getPosition() % 3 == 0) {
                            holder.setStyle(XRichText.Style.LEFT);
                        } else if (holder.getPosition() % 3 == 1) {
                            holder.setStyle(XRichText.Style.CENTER);
                        } else {
                            holder.setStyle(XRichText.Style.RIGHT);
                        }

                        //设置宽高
                        holder.setWidth(550);
                        holder.setHeight(400);
                    }
                })
                .imageDownloader(new ImageLoader() {
                    @Override
                    public Bitmap getBitmap(String url) throws IOException {
                        //Glide loader
                        Bitmap bmp = null;
                        FutureTarget<Bitmap> bitmap = Glide.with(NoticeDetailActivity.this)
                                .asBitmap()
                                .load(url)
                                .submit();
                        try {
                            bmp = bitmap.get();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return bmp;
                    }
                })
                .text(TEXT);
    }

    @OnClick(R.id.back_img)
    public void onViewClicked() {
        onBackPressed();
    }
}
