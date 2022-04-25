import axios from "axios";
import { useCallback } from "react";
import { useNavigate } from "react-router";
import styled from "styled-components";
import theme from "../../theme";
import useSWR from "swr";
import fetcher from "../../utils/fetcher";
import { Link } from "react-router-dom";

const MenuBar = (props) => {
  const navigate = useNavigate();

  const registerHandler = () => {
    navigate("/register");
  };

  const loginHandler = () => {
    navigate("/login");
  };

  const modalHandler = () => {
    props.setModal(!props.modal);
  };

  const onLogout = useCallback(() => {
    // axios
    //   .post(
    //     "/api/users/logout",
    //     { id: window.localStorage.getItem("data").id },
    //     { withCredentials: true }
    //   )
    //   .then(() => window.localStorage.clear());
    window.localStorage.clear();
    console.log(1);
  });

  return (
    <Wrapper>
      <HeadSection>
        {window.localStorage.getItem("data") ? (
          <div>
            <button onClick={onLogout}>로그아웃</button>
          </div>
        ) : (
          <div>
            <div onClick={registerHandler}>회원가입</div>
            <div onClick={loginHandler}>로그인</div>
          </div>
        )}
      </HeadSection>
      <BodySection>
        <button
          type="button"
          onClick={() => {
            navigate("/");
            modalHandler();
          }}
        >
          MOMO
        </button>
        <button
          type="button"
          onClick={() => {
            navigate("/beamomo");
            modalHandler();
          }}
        >
          MOMO 되기
        </button>
        <button
          type="button"
          onClick={() => {
            navigate("/rules");
            modalHandler();
          }}
        >
          MOMO 규칙
        </button>
        <button
          type="button"
          onClick={() => {
            navigate("/mymomo");
            modalHandler();
          }}
        >
          My MOMO
        </button>
        <button
          type="button"
          onClick={() => {
            navigate("/");
            modalHandler();
          }}
        >
          My Question
        </button>
        <button
          type="button"
          onClick={() => {
            navigate("/momoshare");
            modalHandler();
          }}
        >
          MOMO Library
        </button>
        <button
          type="button"
          onClick={() => {
            navigate("/");
            modalHandler();
          }}
        >
          MOMO 알람
        </button>
      </BodySection>
      <FooterSection>
        <button
          type="button"
          onClick={() => {
            navigate("/about");
            modalHandler();
          }}
        >
          About Us
        </button>
        <button
          type="button"
          onClick={() => {
            navigate("/contact");
            modalHandler();
          }}
        >
          Contact Us
        </button>
      </FooterSection>
    </Wrapper>
  );
};

export default MenuBar;

const Backdrop = styled.div`
  width: 100vh;
  height: 100vh;
  background-color: rgba(255, 255, 255, 0);
  z-index: 1;
`;

const Wrapper = styled.div`
  /* top: 5rem; */
  width: 40vw;
  height: 80vh;
  position: fixed;
  background-color: white;
  border: 2px solid black;
  border-left: none;
  z-index: 2;
`;

const HeadSection = styled.section`
  display: flex;
  justify-content: space-around;
  /* margin: 1rem 0.5rem;
  padding-bottom: 2rem; */
  > div {
    display: flex;
    justify-content: space-around;
    text-decoration: underline;
    margin: 1rem 0.5rem;
    padding-bottom: 2rem;
    font-size: 0.9rem;
    > div:first-child {
      margin-right: 1rem;
    }
  }
`;

const BodySection = styled.section`
  text-align: center;
  padding-bottom: 13rem;
  > button {
    padding-bottom: 1rem;
    font-size: 1rem;
    font-family: ${theme.font.basic_font};
  }
`;

const FooterSection = styled.section`
  text-align: center;

  > button {
    padding-bottom: 1rem;
    font-size: 1rem;
    font-family: ${theme.font.basic_font};
  }
`;

const Linkto = styled(Link)`
  text-decoration: none;
  font-size: 1.3rem;
  color: black;
`;
