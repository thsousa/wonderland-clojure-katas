(ns alphabet-cipher.coder-test
  (:require [clojure.test :refer :all]
            [alphabet-cipher.coder :refer :all]))

(deftest test-extended-keyword
  (testing "extend a keyword by repeating its chars"
    (is (= [\a \b \c \a \b]
           (extended-keyword "abc" 5)))))

(deftest test-int->char
  (testing "can transform an int into a char"
    (is (= \a (int->char 0)))
    (is (= \z (int->char 25)))))

(deftest test-rotate
  (testing "rotate a letter"
    (is (= \b (rotate-letter + \a \b)))
    (is (= \z (rotate-letter - \a \b)))))

(deftest test-encode
  (testing "can encode a message with a secret keyword"
    (is (= "hmkbxebpxpmyllyrxiiqtoltfgzzv"
           (encode "vigilance" "meetmeontuesdayeveningatseven")))
    (is (= "egsgqwtahuiljgs"
           (encode "scones" "meetmebythetree")))))


(deftest test-decode
  (testing "can decode a message given an encoded message and a secret keyword"
    (is (= "meetmeontuesdayeveningatseven"
           (decode "vigilance" "hmkbxebpxpmyllyrxiiqtoltfgzzv")))
    (is (= "meetmebythetree"
           (decode "scones" "egsgqwtahuiljgs")))))

(deftest test-decipher
  (testing "can extract the secret keyword given an encrypted message and the original message"
    (is (= "vigilance"
           (decipher "opkyfipmfmwcvqoklyhxywgeecpvhelzg" "thequickbrownfoxjumpsoveralazydog")))
    (is (= "scones"
           (decipher "hcqxqqtqljmlzhwiivgbsapaiwcenmyu" "packmyboxwithfivedozenliquorjugs")))
    (is (= "abcabcx"
           (decipher "hfnlphoontutufa" "hellofromrussia")))))
