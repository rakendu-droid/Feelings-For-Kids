package emotions.kids.com.psychology;

import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by rakendu on 03/01/15.
 */
public class FeelingModel implements Serializable {

    String name;
    String desc;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    byte[] image1Bytes;
    byte[] image2Bytes;
    byte[] image3Bytes;
    int temp;

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImageView1(ImageView imageView1) {
        this.imageView1 = imageView1;
    }

    public void setImageView2(ImageView imageView2) {
        this.imageView2 = imageView2;
    }

    public void setImageView3(ImageView imageView3) {
        this.imageView3 = imageView3;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public ImageView getImageView1() {
        return imageView1;
    }

    public ImageView getImageView2() {
        return imageView2;
    }

    public ImageView getImageView3() {
        return imageView3;
    }

    public int getTemp() {
        return temp;
    }
}
