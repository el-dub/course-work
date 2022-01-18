import {constants} from "@/components/mixins/constants";
export const services = {
    methods: {
        getStatisticsByCategory: function(filter) {
            const url = '/statistics/categories';
            const vacanciesData = this.axios.post(url, filter);
            console.log(vacanciesData);
            return vacanciesData;
        },
        getStatisticsByCompanies: function(filter) {
            const url = '/statistics/companies';
            const vacanciesData = this.axios.post(url, filter);
            console.log(vacanciesData);
            return vacanciesData;
        },
        getCategories: function() {
            const url = '/categories';
            const categoriesData = this.axios.get(url);
            console.log(categoriesData);
            return categoriesData;
        },
        getCompanies: function() {
            const url = '/companies';
            const companiesData = this.axios.get(url);
            console.log(companiesData);
            return companiesData;
        },
        getLevels: function() {
            const url = '/levels';
            const levelsData = this.axios.get(url);
            console.log(levelsData);
            return levelsData;
        },
        getLocations: function() {
            const url = '/locations';
            const locationsData = this.axios.get(url);
            console.log(locationsData);
            return locationsData;
        },
        getChartData: function(fullData) {
            console.log(fullData);
            if (fullData.items.length <= constants.data().COLORS.length) {
                return fullData.items;
            } else {
                const newItems = fullData.items.slice(0, constants.data().COLORS.length - 1);
                const other = fullData.items.slice(constants.data().COLORS.length - 1, fullData.items.len);
                newItems.push({name: "Other", number: other.map(el => el.number).reduce((sum, current) => sum + current)});
                return newItems;
            }
        }
    }
};