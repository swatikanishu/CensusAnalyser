package com.censusAnalyser;
import com.censusanalyser.CensusAnalyser;
import com.censusanalyser.CensusAnalyserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

    public class CensusAnalyserTest {


        private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
        private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
        private static final String WRONG_CSV_FILETYPE_PATH = "./src/test/resources/IndiaStateCensusData.pdf";
        private static final String WRONG_CSV_FILE_DELIMITER = "./src/test/resources/IndiaStateCensusDataDelimiter.csv";
        private static final String WRONG_CSV_FILE_WRONG_HEADER = "./src/test/resources/IndiaStateCensusDataWrongHeader.csv";

        @Test
        public void givenIndianCensusCSVFileReturnsCorrectRecords() {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser();
                int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
                Assertions.assertEquals(29, numOfRecords);
            } catch (CensusAnalyserException e) {
            }
        }

        @Test
        public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser();
                ExpectedException exceptionRule = ExpectedException.none();
                exceptionRule.expect(CensusAnalyserException.class);
                censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
            } catch (CensusAnalyserException e) {
                Assertions.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
            }
        }
        @Test
        public void givenIndiaCensusData_WithWrongFileType_ShouldThrowException() {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser();
                ExpectedException exceptionRule = ExpectedException.none();
                exceptionRule.expect(CensusAnalyserException.class);
                censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILETYPE_PATH);
            } catch (CensusAnalyserException e) {
                Assertions.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_TYPE_PROBLEM,e.type);
            }
        }
        @Test
        public void givenIndiaCensusData_WithWrongFileDelimiter_ShouldThrowException() {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser();
                ExpectedException exceptionRule = ExpectedException.none();
                exceptionRule.expect(CensusAnalyserException.class);
                censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_DELIMITER);
            } catch (CensusAnalyserException e) {
                Assertions.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
            }
        }
        @Test
        public void givenIndiaCensusData_WithWrongFileHeaderIncorrect_ShouldThrowException() {
            try {
                CensusAnalyser censusAnalyser = new CensusAnalyser();
                ExtendedException exceptionRule = ExpectedException.none();
                exceptionRule.expect(CensusAnalyserException.class);
                censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_WRONG_HEADER);
            } catch (CensusAnalyserException e) {
                Assertions.assertEquals(CensusAnalyserException.ExceptionType.WRONG_HEADER,e.type);
            }
        }
    }