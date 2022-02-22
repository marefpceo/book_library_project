import { useRef, useState, useEffect } from "react";
import { Link } from "react-router-dom";
import {
  faCheck,
  faTimes,
  faInfoCircle,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import axios from "../api/axios";

const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
const NORMAL_REGEX = /^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/;
const NUMBER_REGEX = /^^[0-9]+$/;
const REGISTER_URL = "/api/user/register";

const RegistrationForm = () => {
  const userRef = useRef();
  const errRef = useRef();

  const [firstName, setFirstName] = useState("");
  const [validFirstName, setValidFirstName] = useState(false);
  const [firstNameFocus, setFirstNameFocus] = useState(false);

  const [lastName, setLastName] = useState("");
  const [validLastName, setValidLastName] = useState(false);
  const [lastNameFocus, setLastNameFocus] = useState(false);

  const [userName, setUserName] = useState("");
  const [validUserName, setValidUserName] = useState(false);
  const [userNameFocus, setUserNameFocus] = useState(false);

  const [pwd, setPwd] = useState("");
  const [validPwd, setValidPwd] = useState(false);
  const [pwdFocus, setPwdFocus] = useState(false);

  const [matchPwd, setMatchPwd] = useState("");
  const [validMatch, setValidMatch] = useState(false);
  const [matchFocus, setMatchFocus] = useState(false);

  const [favoriteGenres, setFavoriteGenres] = useState("");
  const [validFavoriteGenres, setValidFavoriteGenres] = useState(false);
  const [favoriteGenresFocus, setFavoriteGenresFocus] = useState(false);

  const [avgReadingHrsDaily, setAvgReadingHrsDaily] = useState("");
  const [validAvgReadingHrsDaily, setValidAvgReadingHrsDaily] = useState(false);
  const [avgReadingHrsDailyFocus, setAvgReadingHrsDailyFocus] = useState(false);

  const [errMsg, setErrMsg] = useState("");
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    userRef.current.focus();
  }, []);

  
  useEffect(() => {
    setValidFirstName(NORMAL_REGEX.test(firstName));
  }, [firstName]);
  
  useEffect(() => {
    setValidLastName(NORMAL_REGEX.test(lastName));
  }, [lastName]);
  
  useEffect(() => {
    setValidUserName(USER_REGEX.test(userName));
  }, [userName]);

  useEffect(() => {
    setValidPwd(PWD_REGEX.test(pwd));
    setValidMatch(pwd === matchPwd);
  }, [pwd, matchPwd]);

  useEffect(() => {
    setValidFavoriteGenres(NORMAL_REGEX.test(favoriteGenres));
  }, [favoriteGenres]);

  useEffect(() => {
    setValidAvgReadingHrsDaily(NUMBER_REGEX.test(avgReadingHrsDaily));
  }, [avgReadingHrsDaily]);

  useEffect(() => {
    setErrMsg("");
  }, [firstName, lastName, userName, pwd, matchPwd]);

  const handleSubmit = async (e) => {
    e.preventDefault()
    // if button enabled with JS hack
    const v1 = USER_REGEX.test(userName);
    const v2 = PWD_REGEX.test(pwd);
    if (!v1 || !v2) {
      setErrMsg("Invalid Entry");
      return;
    }

    try {
      const response = await axios({
        method: 'POST',
        url: REGISTER_URL,
        headers: { 'Content-Type': 'application/json' },
        data: JSON.stringify({
          "firstName": firstName,
          "lastName": lastName,
          "username": userName,
          "password": pwd,
          "favoriteGenres": favoriteGenres,
          "avgReadingHrsDaily": avgReadingHrsDaily,
        }),
      })
      console.log(response.data);
      console.log(JSON.stringify(response));
      setSuccess(true);
    } catch (err) {
      if (!err?.response) {
        setErrMsg("No Server Response");
      } else if (err.response?.status === 409) {
        setErrMsg("Username Taken");
      } else {
        setErrMsg("Registration Failed");
      }
      errRef.current.focus();
    }
  };

  return (
    <>
    {
      success ? (
        // <section className="w-128 flex justify-center flex-col self-center shadow-md bg-white rounded px-8 pt-6 pb-8 mb-4 font-['Roboto]">
        //   <h1 className="text-center text-4xl text-lime-500 mb-10">Registration Successful!</h1>
        //   <p className="flex justify-center">
        //   <Link
        //     to="/login"
        //     className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
        //   >
        //     Login
        //   </Link>
        //   </p>
        // </section>
        <section
          className="relative top-20 mx-auto p-5 border w-96 shadow-lg rounded-md bg-white"
        >
          <div className="mt-3 flex flex-col text-center">
            <div
              className="mx-auto flex items-center justify-center h-12 w-12 rounded-full bg-green-100"
            >
              <svg
                className="h-6 w-6 text-green-600"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M5 13l4 4L19 7"
                ></path>
              </svg>
            </div>
            <h3 className="text-lg leading-6 font-medium text-gray-900">Successful!</h3>
            <div className="mt-2 px-7 py-3">
              <p className="text-sm text-gray-500">
                Account has been successfully registered!
              </p>
            </div>
            <div className="items-center px-4 py-3">
            <Link
            to="/login"
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
          >
            Login
          </Link>
            </div>
          </div>
        </section>
      ) : (
    <section>
      <p
        ref={errRef}
        className={errMsg ? "bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" : "offscreen"}
        aria-live="assertive"
      >
        {errMsg}
      </p>
      <h1 className="text-center text-4xl text-gray-500 mb-10">Register</h1>
      <form
        onSubmit={handleSubmit}
        className="w-128 flex justify-center flex-col self-center shadow-md bg-white rounded px-8 pt-6 pb-8 mb-4 font-['Roboto]"
      >
        <div className="flex mb-3">
          <div className="flex-1 flex flex-col pr-2">
            <label
              htmlFor="firstname"
              className="block text-gray-700 text-sm mb-2"
            >
              First Name
              <FontAwesomeIcon
                icon={faCheck}
                className={validFirstName ? "pl-2 text-lime-600" : "invisible"}
              />
              <FontAwesomeIcon
                icon={faTimes}
                className={
                  validFirstName || !firstName ? "invisible" : "text-red-700"
                }
              />
            </label>
            <input
              type="text"
              id="firstname"
              autoComplete="off"
              onChange={(e) => setFirstName(e.target.value)}
              value={firstName}
              required
              aria-invalid={validFirstName ? "false" : "true"}
              aria-describedby="fnnote"
              onFocus={() => setFirstNameFocus(true)}
              onBlur={() => setFirstNameFocus(false)}
              className={
                "w-full shadow appearance-none border rounded py-2 px-2 text-gray-700 leading-tight focus:outline-none focus:shadow-outline placeholder-red-700 text-base rounded-lg "
              }
            />
            <p
              id="fnnote"
              className={
                firstNameFocus && firstName && !validFirstName
                  ? "instructions"
                  : "offscreen"
              }
            >
              <FontAwesomeIcon icon={faInfoCircle} />
              4 to 24 characters.
              <br />
              Must begin with a letter.
              <br />
              Letters, numbers, underscores, hyphens allowed.
            </p>
          </div>
          <div className="flex-1 flex flex-col pl-2">
            <label
              htmlFor="lastname"
              className="block text-gray-700 text-sm mb-2"
            >
              Last Name
              <FontAwesomeIcon
                icon={faCheck}
                className={validLastName ? "pl-2 text-lime-600" : "invisible"}
              />
              <FontAwesomeIcon
                icon={faTimes}
                className={
                  validLastName || !lastName ? "invisible" : "text-red-700"
                }
              />
            </label>
            <input
              type="text"
              id="lastname"
              autoComplete="off"
              onChange={(e) => setLastName(e.target.value)}
              value={lastName}
              required
              aria-invalid={validLastName ? "false" : "true"}
              aria-describedby="lnnote"
              onFocus={() => setLastNameFocus(true)}
              onBlur={() => setLastNameFocus(false)}
              className={
                "w-full shadow appearance-none border rounded py-2 px-2 text-gray-700 leading-tight focus:outline-none focus:shadow-outline placeholder-red-700 text-base rounded-lg "
              }
            />
            <p
              id="lnnote"
              className={
                lastNameFocus && lastName && !validLastName
                  ? "instructions"
                  : "offscreen"
              }
            >
              <FontAwesomeIcon icon={faInfoCircle} />
              4 to 24 characters.
              <br />
              Must begin with a letter.
              <br />
              Letters, numbers, underscores, hyphens allowed.
            </p>
          </div>
        </div>
        <div className="flex flex-col mb-3">
          <label
            htmlFor="username"
            className="block text-gray-700 text-sm mb-2"
          >
            Username
            <FontAwesomeIcon
              icon={faCheck}
              className={validUserName ? "pl-2 text-lime-600" : "invisible"}
            />
            <FontAwesomeIcon
              icon={faTimes}
              className={
                validUserName || !userName ? "invisible" : "text-red-700"
              }
            />
          </label>
          <input
            type="text"
            id="username"
            ref={userRef}
            autoComplete="off"
            onChange={(e) => setUserName(e.target.value)}
            value={userName}
            required
            aria-invalid={validUserName ? "false" : "true"}
            aria-describedby="uinote"
            onFocus={() => setUserNameFocus(true)}
            onBlur={() => setUserNameFocus(false)}
            className={
              "w-full shadow appearance-none border rounded py-2 px-2 text-gray-700 leading-tight focus:outline-none focus:shadow-outline placeholder-red-700 text-base rounded-lg "
            }
          />
          <p
            id="uidnote"
            className={
              userNameFocus && userName && !validUserName
                ? "instructions"
                : "offscreen"
            }
          >
            <FontAwesomeIcon icon={faInfoCircle} />
            4 to 24 characters.
            <br />
            Must begin with a letter.
            <br />
            Letters, numbers, underscores, hyphens allowed.
          </p>
        </div>
        <div className="flex flex-col mb-3">
          <label htmlFor="password" className="block text-gray-700 text-sm mb-2">
            Password
            <FontAwesomeIcon
              icon={faCheck}
              className={validPwd ? "pl-2 text-lime-600" : "invisible"}
            />
            <FontAwesomeIcon
              icon={faTimes}
              className={validPwd || !pwd ? "invisible" : "text-red-700"}
            />
          </label>
          <input
            type="password"
            id="password"
            onChange={(e) => setPwd(e.target.value)}
            value={pwd}
            required
            aria-invalid={validPwd ? "false" : "true"}
            aria-describedby="pwdnote"
            onFocus={() => setPwdFocus(true)}
            onBlur={() => setPwdFocus(false)}
            className={
              "shadow appearance-none border rounded w-full py-2 px-2 text-gray-700 leading-tight focus:outline-none focus:shadow-outline placeholder-red-700 text-base rounded-lg "
            }
          />
          <p
            id="pwdnote"
            className={pwdFocus && !validPwd ? "instructions" : "offscreen"}
          >
            <FontAwesomeIcon icon={faInfoCircle} />
            8 to 24 characters.
            <br />
            Must include uppercase and lowercase letters, a number and a special
            character.
            <br />
            Allowed special characters:{" "}
            <span aria-label="exclamation mark">!</span>{" "}
            <span aria-label="at symbol">@</span>{" "}
            <span aria-label="hashtag">#</span>{" "}
            <span aria-label="dollar sign">$</span>{" "}
            <span aria-label="percent">%</span>
          </p>
        </div>
        <div className="flex flex-col mb-3">
          <label htmlFor="confirm_pwd" className="block text-gray-700 text-sm mb-2">
            Confirm Password
            <FontAwesomeIcon
              icon={faCheck}
              className={
                validMatch && matchPwd ? "pl-2 text-lime-600" : "invisible"
              }
            />
            <FontAwesomeIcon
              icon={faTimes}
              className={validMatch || !matchPwd ? "invisible" : "text-red-700"}
            />
          </label>
          <input
            type="password"
            id="confirm_pwd"
            onChange={(e) => setMatchPwd(e.target.value)}
            value={matchPwd}
            required
            aria-invalid={validMatch ? "false" : "true"}
            aria-describedby="confirmnote"
            onFocus={() => setMatchFocus(true)}
            onBlur={() => setMatchFocus(false)}
            className={
              "shadow appearance-none border rounded w-full py-2 px-2 text-gray-700 leading-tight focus:outline-none focus:shadow-outline placeholder-red-700 text-base rounded-lg "
            }
          />
          <p
            id="confirmnote"
            className={matchFocus && !validMatch ? "instructions" : "offscreen"}
          >
            <FontAwesomeIcon icon={faInfoCircle} />
            Must match the first password input field.
          </p>
        </div>
        <div className="flex flex-col mb-3">
          <label htmlFor="favorite_genres" className="block text-gray-700 text-sm mb-2">
            Favorite Genres
            <FontAwesomeIcon
              icon={faCheck}
              className={
                validFavoriteGenres && favoriteGenres
                  ? "pl-2 text-lime-600"
                  : "invisible"
              }
            />
            <FontAwesomeIcon
              icon={faTimes}
              className={
                validFavoriteGenres || !favoriteGenres
                  ? "invisible"
                  : "text-red-700"
              }
            />
          </label>
          <input
            type="text"
            id="favorite_genres"
            onChange={(e) => setFavoriteGenres(e.target.value)}
            value={favoriteGenres}
            required
            aria-invalid={validFavoriteGenres ? "false" : "true"}
            aria-describedby="fgnote"
            onFocus={() => setFavoriteGenresFocus(true)}
            onBlur={() => setFavoriteGenresFocus(false)}
            className={
              "shadow appearance-none border rounded w-full py-2 px-2 text-gray-700 leading-tight focus:outline-none focus:shadow-outline placeholder-red-700 text-base rounded-lg "
            }
          />
          <p
            id="fgnote"
            className={
              favoriteGenresFocus && !validFavoriteGenres
                ? "instructions"
                : "offscreen"
            }
          >
            <FontAwesomeIcon icon={faInfoCircle} />
            Just input
          </p>
        </div>
        <div className="flex flex-col mb-3">
          <label htmlFor="avgReadingHrsDaily" className="block text-gray-700 text-sm mb-2">
            Avg Reading Hrs Daily
            <FontAwesomeIcon
              icon={faCheck}
              className={
                validAvgReadingHrsDaily && avgReadingHrsDaily
                  ? "pl-2 text-lime-600"
                  : "invisible"
              }
            />
            <FontAwesomeIcon
              icon={faTimes}
              className={
                validAvgReadingHrsDaily || !avgReadingHrsDaily
                  ? "invisible"
                  : "text-red-700"
              }
            />
          </label>
          <input
            type="number"
            id="avgReadingHrsDaily"
            onChange={(e) => setAvgReadingHrsDaily(e.target.value)}
            value={avgReadingHrsDaily}
            required
            aria-invalid={validAvgReadingHrsDaily ? "false" : "true"}
            aria-describedby="arhdnote"
            onFocus={() => setAvgReadingHrsDailyFocus(true)}
            onBlur={() => setAvgReadingHrsDailyFocus(false)}
            className={
              "shadow appearance-none border rounded w-full py-2 px-2 text-gray-700 leading-tight focus:outline-none focus:shadow-outline placeholder-red-700 text-base rounded-lg "
            }
          />
          <p
            id="arhdnote"
            className={
              avgReadingHrsDailyFocus && !validAvgReadingHrsDaily
                ? "instructions"
                : "offscreen"
            }
          >
            <FontAwesomeIcon icon={faInfoCircle} />
            Just input
          </p>
        </div>
        <button
          className="mt-6 text-white w-full rounded-full bg-blue-500 hover:bg-blue-700:text-white font-bold py-2 px-4 round"
          disabled={!validUserName || !validPwd || !validMatch ? true : false}
        >
          Register
        </button>
      </form>
      <p className="flex mt-14 justify-center">
        Already registered?
        <br />
        <span className="pl-2">
          <Link
            to="/login"
            className="block py-2 pr-4 pl-3 text-gray-700 border-b border-gray-100 hover:bg-gray-300 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-gray-400 md:dark:hover:text-gray-900 dark:hover:bg-gray-700 dark:hover:text-gray-300 md:dark:hover:bg-transparent dark:border-gray-700"
          >
            Login
          </Link>
        </span>
      </p>
    </section>
      )}
    </>
  );
};

export default RegistrationForm;
