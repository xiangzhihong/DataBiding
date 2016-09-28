# DataBiding

# 配置环境

默认需要将Android studio升级到1.3（估计现在大部分都满足），这是因为databinding的build.gradle需要满足最低1.3（Android Studio 已经内置了对 Android Data Binding 框架的支持）。使用的时候只需要在build.gradle添加下面的脚本：

[html] view plain copy print?在CODE上查看代码片派生到我的代码片
dataBinding {  
        enabled = true  
    }  
Data Binding 是一个 support 包，添加完后，你会发现我们的External Libraries中多了四个aar包：
[html] view plain copy print?在CODE上查看代码片派生到我的代码片
adapters-1.1    定义了一些DataBinding的组件  
baseLibrary-2.1.3    定义了一些DataBinding的annotation和回调接口  
compiler-2.1.3    定义了一些用于编译DataBinding的工具类  
library-1.1     定义了一些Observable基本类型  
DataBinding库改变了android传统开发流程中Layout文件的编写方式，通过ViewModel，将视图和Model绑定在一起，你只需要修改Model层的值，对应的View层就会监听到自动修改自身。（其实也就是达到了页面和数据的分离）

讲了这么多理论的东西，那么到底怎么使用DataBinding呢？
首先我们需要写一个layout，不过Data Binding layout的和传统的layout的写法不一样，起始根标签是 layout，接下来一个 data 元素以及一个 view 的根元素。这个 view 元素就是你没有使用 Data Binding的layout文件的根元素。
一般在正式写代码的顺序上我们会先定义一个viewmodel类，如下：
[html] view plain copy print?在CODE上查看代码片派生到我的代码片
<span style="font-size:12px;">public class UserModel {  
    private  String firstName;  
    private  String lastName;  
    public UserModel(String firstName, String lastName) {  
        this.firstName = firstName;  
        this.lastName = lastName;  
    }  
    public String getFirstName() {  
        return this.firstName;  
    }  
    public String getLastName() {  
        return this.lastName;  
    }  
}</span>  

然后在实现一个布局（技巧就在这里面）：
[html] view plain copy print?在CODE上查看代码片派生到我的代码片
<span style="color:#555555;"><?xml version="1.0" encoding="utf-8"?>  
<layout xmlns:android="http://schemas.android.com/apk/res/android">  
    <data>  
        <variable  
            name="user"  
           </span><span style="color:#ff0000;"> type="com.xzh.databinding.model.UserModel" /></span><span style="color:#555555;">  
    </data>  
  
    <LinearLayout  
        android:layout_width="match_parent"  
        android:layout_height="match_parent"  
        android:orientation="vertical">  
  
        <TextView  
            android:layout_width="wrap_content"  
            android:layout_height="wrap_content"  
            android:text="</span><span style="color:#ff0000;">@{user.firstName}</span><span style="color:#555555;">" />  
  
        <TextView  
            android:layout_width="wrap_content"  
            android:layout_height="wrap_content"  
            android:text="</span><span style="color:#ff0000;">@{user.lastName}</span><span style="color:#555555;">" />  
    </LinearLayout>  
</layout></span>  
请注意这个layout的文件名，DataBinding会根据这个layout的文件名生成一个xxBinding类，这个类继承自ViewDataBiding；如果layout文件名是content_main.xml,则会生成一个ContentMainBinding类，根据官方解释是自动把layout文件名的下滑线去掉，然后采用驼峰式的命名规则，然后再加上Binding后缀。
com.xzh.databinding会根据xml文件的名称 Generate 一个继承自 ViewDataBinding 的类。例如，这里 xml 的文件名叫 activity_main.xml，那么生成的类就是 ActivityMainBinding。
最后需要实现通过ViewModel实现View和Model的数据绑定（常常写在Activity层，如果项目比较大的话，建议将网络请求单独分层 ）。
[html] view plain copy print?在CODE上查看代码片派生到我的代码片
private void getSearchData(String search) {  
       binding.progressBar.setVisibility(View.VISIBLE);  
       MovieHttpManager.searchMovies(search, new MovieHttpManager.IMovieResponse<List<Movie>>() {  
           @Override  
           public void onData(List<Movie> list) {  
               MovieAdapter mAdapter = new MovieAdapter(MovieActivity.this, list);  
               binding.recyclerView.setAdapter(mAdapter);  
               binding.progressBar.setVisibility(View.GONE);  
           }  
       });  
   }   

# 演示效果
   ![ABC](https://github.com/xiangzhihong/DataBiding/blob/master/screen/device-2016-09-28-132140.png) 
