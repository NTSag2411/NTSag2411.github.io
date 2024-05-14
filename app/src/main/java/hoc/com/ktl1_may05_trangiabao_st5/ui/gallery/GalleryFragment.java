package hoc.com.ktl1_may05_trangiabao_st5.ui.gallery;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import hoc.com.ktl1_may05_trangiabao_st5.R;
import hoc.com.ktl1_may05_trangiabao_st5.databinding.FragmentGalleryBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private Spinner spinnerManufacturers;
    private ArrayAdapter<CharSequence> defaultAdapter;
    private ArrayAdapter<CharSequence> filteredAdapter;
    private List<String> productList;
    private String currentProduct;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        spinnerManufacturers = binding.spinnerManufacturers;
        defaultAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.manufacturers_array, android.R.layout.simple_spinner_item);
        defaultAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerManufacturers.setAdapter(defaultAdapter);

        RadioGroup radioGroupType = binding.radioGroupType;
        productList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.all_products_array)));

        radioGroupType.setOnCheckedChangeListener((group, checkedId) -> {
            int selectedId = radioGroupType.getCheckedRadioButtonId();
            RadioButton radioButton = root.findViewById(selectedId);
            String type = radioButton.getText().toString();

            if (type.equals(getString(R.string.radio_laptop))) {
                filteredAdapter = ArrayAdapter.createFromResource(getContext(),
                        R.array.limited_manufacturers_array, android.R.layout.simple_spinner_item);
                filteredAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerManufacturers.setAdapter(filteredAdapter);

                productList.clear();
                productList.add("HP Stream 13");
                productList.add("HP Stream 14");
                productList.add("HP Stream 15");
            } else if (type.equals(getString(R.string.radio_phone))) {
                filteredAdapter = ArrayAdapter.createFromResource(getContext(),
                        R.array.limited_manufacturers_array, android.R.layout.simple_spinner_item);
                filteredAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerManufacturers.setAdapter(filteredAdapter);

                productList.clear();
                productList.add("Asus Zenfone");
                productList.add("Asus Zen");
                productList.add("Asus Z");
            } else {
                spinnerManufacturers.setAdapter(defaultAdapter);
                productList.clear();
                productList.addAll(Arrays.asList(getResources().getStringArray(R.array.all_products_array)));
            }
        });

        Button buttonSearch = binding.buttonSearch;
        TextView textViewProduct = binding.textViewProduct;

        buttonSearch.setOnClickListener(v -> {
            StringBuilder productsText = new StringBuilder();
            for (String product : productList) {
                productsText.append(product).append("\n");
            }
            textViewProduct.setText(productsText.toString());
            if (!productList.isEmpty()) {
                currentProduct = productList.get(0);
            }
        });

        textViewProduct.setOnLongClickListener(v -> {
            new AlertDialog.Builder(getContext())
                    .setTitle("Xác nhận xóa")
                    .setMessage("Bạn có chắc chắn muốn xóa " + currentProduct + " không?")
                    .setPositiveButton("Có", (dialog, which) -> {
                        productList.remove(currentProduct);
                        StringBuilder productsText = new StringBuilder();
                        for (String product : productList) {
                            productsText.append(product).append("\n");
                        }
                        textViewProduct.setText(productsText.toString());
                        Toast.makeText(getContext(), currentProduct + " đã được xóa", Toast.LENGTH_SHORT).show();
                        if (!productList.isEmpty()) {
                            currentProduct = productList.get(0);
                        } else {
                            currentProduct = "";
                        }
                    })
                    .setNegativeButton("Không", null)
                    .show();
            return true;
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
