package com.xzh.databinding.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.xzh.databinding.BR;

import java.io.Serializable;

public class Movie extends BaseObservable {

    private String id;
    private String title;
    private String original_title;
    private String year;
    private Images images;
    private Rating rating;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);

    }

    @Bindable
    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
        notifyPropertyChanged(BR.description);
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
        notifyPropertyChanged(BR.images);
    }

    @Bindable
    public String getDescription() {
        return this.original_title + "\n" + this.getYear();
    }


    @Bindable
    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
        notifyPropertyChanged(BR.rating);
    }

    public class Images implements Serializable {
        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }


    public class Rating {
        private float max;
        private float average;
        private String stars;
        private float min;

        public float getMax() {
            return max;
        }

        public void setMax(float max) {
            this.max = max;
        }

        public float getAverage() {
            return average;
        }

        public void setAverage(float average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public float getMin() {
            return min;
        }

        public void setMin(float min) {
            this.min = min;
        }
    }

    public class Cast {
        private String id;
        private String name;
        private Images avatars;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Images getAvatars() {
            return avatars;
        }

        public void setAvatars(Images avatars) {
            this.avatars = avatars;
        }
    }



}
