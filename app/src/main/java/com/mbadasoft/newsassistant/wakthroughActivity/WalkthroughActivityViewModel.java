package com.mbadasoft.newsassistant.wakthroughActivity;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.mbadasoft.newsassistant.data.AppNewsRepository;
import com.mbadasoft.newsassistant.data.AppPreferencesRepository;
import com.mbadasoft.newsassistant.models.Category;
import com.mbadasoft.newsassistant.models.Source;
import com.mbadasoft.newsassistant.models.SourcesResult;

import java.util.HashSet;
import java.util.Set;

public class WalkthroughActivityViewModel extends AndroidViewModel {

    private AppNewsRepository newsRepository;
    private AppPreferencesRepository preferencesRepository;
    private Set<String> selectedSources = new HashSet<>();
    private Set<String> selectedCategories = new HashSet<>();

    public WalkthroughActivityViewModel(@NonNull Application application) {
        super(application);
        newsRepository = AppNewsRepository.getInstance(application);
        preferencesRepository = AppPreferencesRepository.getInstance(application);
    }

    public LiveData<SourcesResult> getAvailableSources() {
        return newsRepository.getSources();
    }

    public boolean IsFirstTimeLogin() {
        return preferencesRepository.getIsFirstTimeLogin();
    }

    public void setIsFirstTimeLogin(boolean value) {
        preferencesRepository.saveisFirstTimeLogin(value);
    }

    public void saveUserData() {
        if (!selectedSources.isEmpty()) {
            preferencesRepository.savePreferredSources(selectedSources);
        }

        if (!selectedCategories.isEmpty()) {
            preferencesRepository.savePreferredCategories(selectedCategories);
        }
    }

    public void addSourceToSelection(Source source) {
        selectedSources.add(source.id);
    }

    public void removeSourceFromSelection(Source source) {
        selectedSources.remove(source.id);
    }

    public void addCategoryToSelection(Category category) {
        selectedCategories.add(category.title);
    }

    public void removeCategoryToSelected(Category category) {
        selectedCategories.remove(category.title);

    }

    public Set<String> getSelectedCategories() {
        return selectedCategories;
    }
}
